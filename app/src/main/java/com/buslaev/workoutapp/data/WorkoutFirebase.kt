package com.buslaev.workoutapp.data

import android.util.Log
import com.buslaev.workoutapp.screens.exercises.ExerciseData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.tasks.await

class WorkoutFirebase {

    private val firestore = FirebaseFirestore.getInstance()
    private val exercisesCollection = firestore.collection(EXERCISES_COLLECTION)

    suspend fun getAllExercises(): List<ExerciseData> {
        return try {
            exercisesCollection.get().await().toObjects(ExerciseData::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }
}