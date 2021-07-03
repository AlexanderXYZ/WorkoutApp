package com.buslaev.workoutapp.roomData

import androidx.lifecycle.LiveData

class OwnProgramRepository(private val dao: OwnProgramDao) {

    val readAllProgram: LiveData<List<OwnProgramData>> = dao.getAllPrograms()

    fun insert(programData: OwnProgramData) {
        dao.insertProgram(programData)
    }
}