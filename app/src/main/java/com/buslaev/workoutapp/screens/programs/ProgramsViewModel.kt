package com.buslaev.workoutapp.screens.programs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.buslaev.workoutapp.roomData.AppDatabase
import com.buslaev.workoutapp.roomData.OwnProgramData
import com.buslaev.workoutapp.roomData.OwnProgramRepository
import com.buslaev.workoutapp.utilits.REPOSITORY

class ProgramsViewModel(application: Application) : AndroidViewModel(application) {

    var data: LiveData<List<OwnProgramData>>

    init {
        val dao = AppDatabase.getDatabase(application).getDao()
        REPOSITORY = OwnProgramRepository(dao)
        data = REPOSITORY.readAllProgram
    }

}