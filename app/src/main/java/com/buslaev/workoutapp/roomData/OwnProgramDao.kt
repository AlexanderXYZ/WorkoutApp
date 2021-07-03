package com.buslaev.workoutapp.roomData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OwnProgramDao {

    @Query("SELECT * FROM own_program ORDER BY id ASC")
    fun getAllPrograms(): LiveData<List<OwnProgramData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProgram(program: OwnProgramData)
}