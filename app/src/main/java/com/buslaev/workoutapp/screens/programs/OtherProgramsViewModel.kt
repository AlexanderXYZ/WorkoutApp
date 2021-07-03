package com.buslaev.workoutapp.screens.programs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.buslaev.workoutapp.data.WorkoutFirebase
import com.buslaev.workoutapp.roomData.OwnProgramData
import kotlinx.coroutines.launch

class OtherProgramsViewModel(application: Application) : AndroidViewModel(application) {

    private val mFirebase: WorkoutFirebase = WorkoutFirebase()
    var data = MutableLiveData<List<OwnProgramData>>()

    init {
        viewModelScope.launch {
            //data.postValue(mFirebase.getAllExercises())
        }
    }
}