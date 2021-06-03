package com.dahyun.familylovenotification.wm

import android.content.Context
import android.content.res.Resources
import android.telephony.SmsManager
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.data.FamilyMember
import com.dahyun.familylovenotification.data.repository.FamilyMemberRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltWorker
class SendSmsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val familyMemberRepository: FamilyMemberRepository
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {
            familyMemberRepository.getAllFamilyMember()
                .apply { filterFamilyMember(this) }
                .forEach {
                    sendMessage(it)
                    countUpSendCount(it)
                }
        }
        return Result.success()
    }

    private suspend fun filterFamilyMember(unFilteredFamilyMember: List<FamilyMember>) {
        unFilteredFamilyMember.filter { !it.isSendingMessage }
            .map { updateLastSendTime(it) }
            .filter { it.sendCountLimitByDay > it.sendCountToday }
            .filter { it.lastSendTime.plusHours(it.sendHourInterval.toLong()) < LocalDateTime.now() }
    }

    private suspend fun updateLastSendTime(familyMember: FamilyMember): FamilyMember {
        var checkedFamilyMember = familyMember
        if ((LocalDateTime.now() > familyMember.lastSendTime) &&
            (LocalDateTime.now().dayOfYear != familyMember.lastSendTime.dayOfYear)
        ) {
            checkedFamilyMember = familyMember.copy(sendCountToday = 0)
            familyMemberRepository.insertFamilyMember(checkedFamilyMember)
        }
        return checkedFamilyMember
    }

    private suspend fun countUpSendCount(familyMember: FamilyMember) {
        familyMemberRepository.insertFamilyMember(
            familyMember.copy(
                sendCountToday = familyMember.sendCountToday + 1,
                lastSendTime = LocalDateTime.now()
            )
        )
    }

    private fun sendMessage(familyMember: FamilyMember) {
        SmsManager.getDefault()
            .sendTextMessage(
                familyMember.phoneNumber,
                null,
                applicationContext.getString(R.string.send_message),
                null,
                null
            )
    }
}