package com.example.familylovenotification.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.familylovenotification.model.data.FamilyMember
import com.example.familylovenotification.model.repository.FamilyMemberRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

class DefaultDetailViewModel @Inject constructor(val familyMemberRepository: FamilyMemberRepository) :
    DetailViewModel() {

    override var _familyMember =
        MutableLiveData(
            FamilyMember(
                0,
                "홍길동",
                "01012345678",
                0,
                5,
                5,
                LocalDateTime.now(),
                false
            )
        )

    override val familyMember: LiveData<FamilyMember>
        get() = _familyMember

    override fun insertFamilyMember(familyMember: FamilyMember) {
        viewModelScope.launch {
            familyMemberRepository.insertFamilyMember(familyMember)
        }
    }

    override fun getFamilyMemberById(id: Int) {
        viewModelScope.launch {
            _familyMember.postValue(familyMemberRepository.getFamilyMemberById(id))
        }
    }

    override fun deleteFamilyMember(id: Int) {
        viewModelScope.launch {
            familyMemberRepository.deleteFamilyMember(id)
        }
    }

    override var isEx: LiveData<Boolean> = CustomMutableLive(false)
}

class CustomMutableLive<T> : MutableLiveData<T> {
    constructor() : super()
    constructor(value: T) : super(value)

    override fun getValue(): T? {
        Log.d("debuging get", super.getValue().toString())
        return super.getValue()
    }

    override fun postValue(value: T) {
        Log.d("debuging post", value.toString())
        super.postValue(value)
    }

    override fun setValue(value: T) {
        Log.d("debuging set", value.toString())
        super.setValue(value)
    }
}
