package com.buslaev.workoutapp.data

import android.util.Log
import com.buslaev.workoutapp.screens.exercises.ExerciseData
import com.google.firebase.firestore.FirebaseFirestore

lateinit var FIRESTORE: FirebaseFirestore

const val EXERCISES_COLLECTION = "exercises"

fun initFirebase() {
    FIRESTORE = FirebaseFirestore.getInstance()
}

//fun getExercises(): List<ExerciseData> {
//    FIRESTORE.collection(EXERCISES_COLLECTION)
//        .get()
//        .addOnSuccessListener {
//            for (document in it) {
//                Log.d("fire", "${document.id} => ${document.data}")
//            }
//        }
//        .addOnFailureListener { exception ->
//            Log.w("fire", "Error getting documents.", exception)
//        }
//}