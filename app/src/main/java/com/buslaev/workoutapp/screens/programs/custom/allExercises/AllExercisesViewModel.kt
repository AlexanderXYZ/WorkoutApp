package com.buslaev.workoutapp.screens.programs.custom.allExercises

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.buslaev.workoutapp.data.WorkoutFirebase
import com.buslaev.workoutapp.roomData.AppDatabase
import com.buslaev.workoutapp.roomData.OwnProgramRepository
import com.buslaev.workoutapp.screens.exercises.ExerciseData
import kotlinx.coroutines.launch

class AllExercisesViewModel(application: Application) : AndroidViewModel(application) {

    private val mFirebase: WorkoutFirebase = WorkoutFirebase()
    var data = MutableLiveData<List<ExerciseData>>()
    private val repository:OwnProgramRepository

    init {
        val dao = AppDatabase.getDatabase(application).getDao()
        repository = OwnProgramRepository(dao)
    }

    fun setData(exercise:String) {
        viewModelScope.launch {
            when(exercise){
                "chest"-> data.postValue(mFirebase.getAllExercises())
            }
        }
    }

}