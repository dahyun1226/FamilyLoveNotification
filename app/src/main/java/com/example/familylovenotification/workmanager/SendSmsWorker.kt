package com.example.familylovenotification.workmanager

import android.content.Context
import android.content.res.Resources
import android.telephony.SmsManager
import android.util.Log
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.familylovenotification.R
import com.example.familylovenotification.model.repository.FamilyMemberRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Provider

class SendSmsWorker(
    context: Context,
    workerParams: WorkerParameters,
    val familyMemberRepository: FamilyMemberRepository
) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {
            Log.e("!!!!", familyMemberRepository.getAllFamilyMember().toString())
            familyMemberRepository.getAllFamilyMember().filter { !it.isSendingMessage }
                .filter {
                    if (LocalDateTime.now().dayOfMonth > it.lastSendTime?.dayOfMonth!! ||
                        (LocalDateTime.now().dayOfMonth == 1 &&
                                (it.lastSendTime?.dayOfMonth == 29 ||
                                        it.lastSendTime?.dayOfMonth == 30 ||
                                        it.lastSendTime?.dayOfMonth == 31))
                    ) {
                        it.sendCountToday = 0
                        familyMemberRepository.insertFamilyMember(it.copy(sendCountToday = 0))
                    }
                    return@filter it.sendCountLimitByDay >= it.sendCountToday
                }
                .filter {
                    return@filter it.lastSendTime?.plusHours(it.sendHourInterval.toLong())!! < LocalDateTime.now()
                }
                .forEach {
                    SmsManager.getDefault()
                        .sendTextMessage(
                            it.phoneNumber,
                            null,
                            Resources.getSystem().getString(R.string.sendMessage),
                            null,
                            null
                        )
                    familyMemberRepository.insertFamilyMember(
                        it.copy(
                            sendCountToday = it.sendCountToday + 1,
                            lastSendTime = LocalDateTime.now()
                        )
                    )
                }
            Log.e("!!!!", familyMemberRepository.getAllFamilyMember().toString())
        }
        return Result.success()
    }

    class Factory @Inject constructor(
        private val
        familyMemberRepository: Provider<FamilyMemberRepository>
    ) : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
            return SendSmsWorker(
                appContext,
                params,
                familyMemberRepository.get()
            )
        }
    }
}
