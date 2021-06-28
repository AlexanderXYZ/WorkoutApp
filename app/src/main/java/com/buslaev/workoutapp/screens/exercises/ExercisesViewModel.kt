package com.buslaev.workoutapp.screens.exercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.buslaev.workoutapp.data.WorkoutFirebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExercisesViewModel(application: Application) : AndroidViewModel(application) {

    private val mFirebase: WorkoutFirebase = WorkoutFirebase()
    var data = MutableLiveData<List<ExerciseData>>()
    init {
        setExercises()
    }

    fun setExercises() {
        viewModelScope.launch {
            data.postValue(mFirebase.getAllExercises())
        }
    }
}