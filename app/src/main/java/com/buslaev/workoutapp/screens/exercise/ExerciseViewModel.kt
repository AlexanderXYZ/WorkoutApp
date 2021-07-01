package com.buslaev.workoutapp.screens.exercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.buslaev.workoutapp.data.WorkoutFirebase
import com.buslaev.workoutapp.screens.exercises.ExerciseData
import kotlinx.coroutines.launch

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val mFirebase: WorkoutFirebase = WorkoutFirebase()
    var data = MutableLiveData<List<ExerciseData>>()

    fun setExercises(exercise: String) {
        viewModelScope.launch {
            when (exercise) {
                "chest" -> data.postValue(mFirebase.getChestExercises())
            }
        }
    }
}