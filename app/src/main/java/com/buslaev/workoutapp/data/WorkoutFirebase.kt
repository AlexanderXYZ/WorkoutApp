package com.buslaev.workoutapp.data

import android.util.Log
import com.buslaev.workoutapp.screens.exercises.ExerciseData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

const val EXERCISES_COLLECTION = "exercises"
const val CHEST_COLLECTION = "chest"

class WorkoutFirebase {

    private val firestore = FirebaseFirestore.getInstance()
    private val exercisesCollection = firestore.collection(EXERCISES_COLLECTION)
    private val chestCollection = firestore.collection(CHEST_COLLECTION)

    suspend fun getAllExercises(): List<ExerciseData> {
        return try {
            exercisesCollection.get().await().toObjects(ExerciseData::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getChestExercises(): List<ExerciseData> {
        return try {
            chestCollection.get().await().toObjects(ExerciseData::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}