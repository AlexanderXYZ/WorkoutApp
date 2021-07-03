package com.buslaev.workoutapp.roomData

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.buslaev.workoutapp.screens.exercises.ExerciseData
import com.google.gson.Gson

@Entity(tableName = "own_program")
data class OwnProgramData(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var imageUrl: String = "",
    var exercises: List<ExerciseData> = emptyList()
)

class ExercesesConverter {
    @TypeConverter
    fun listToJson(value: List<ExerciseData>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<ExerciseData>::class.java).toList()
}
