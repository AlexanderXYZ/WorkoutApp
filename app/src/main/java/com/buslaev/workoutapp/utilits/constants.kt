package com.buslaev.workoutapp.utilits

import android.content.SharedPreferences
import com.buslaev.workoutapp.MainActivity
import com.buslaev.workoutapp.roomData.OwnProgramRepository

lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: OwnProgramRepository

//Preferences
lateinit var PREFS: SharedPreferences
const val PREF_SETTINGS = "settings"
const val PREF_WEIGHT = "weight"
const val PREF_HEIGHT = "height"
const val PREF_FAT_PERC = "fat_perc"
const val PREF_FAT = "fat"
const val PREF_MUSCLE_PERC = "muscle_perc"
const val PREF_MUSCLE = "muscle"


