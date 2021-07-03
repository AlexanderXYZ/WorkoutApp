package com.buslaev.workoutapp.screens.programs.custom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.buslaev.workoutapp.roomData.OwnProgramData
import com.buslaev.workoutapp.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomProgramViewModel(application: Application) : AndroidViewModel(application) {


    fun addProgram(data: OwnProgramData, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(data)
        }
        onSuccess()
    }
}