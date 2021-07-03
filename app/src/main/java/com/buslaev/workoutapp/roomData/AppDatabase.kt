package com.buslaev.workoutapp.roomData

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [OwnProgramData::class], version = 1)
@TypeConverters(ExercesesConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDao(): OwnProgramDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "custom_programs_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}