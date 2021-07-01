package com.buslaev.workoutapp.screens.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.databinding.FragmentExerciseBinding
import com.buslaev.workoutapp.screens.exercises.ExerciseData
import com.buslaev.workoutapp.screens.exercises.ExercisesAdapter
import com.buslaev.workoutapp.utilits.APP_ACTIVITY


class ExerciseFragment : Fragment(), ExercisesAdapter.OnItemClickListener {

    private var _binding: FragmentExerciseBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mViewModel: ExerciseViewModel
    private lateinit var mObserver: Observer<List<ExerciseData>>

    private lateinit var mAdapter: ExercisesAdapter
    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        mAdapter = ExercisesAdapter(this, APP_ACTIVITY)
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        mViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        val exercise = arguments?.getString("exercise")
        if (exercise != null) {
            mViewModel.setExercises(exercise)
        }
        mObserver = Observer { list ->
            val res = list.sortedBy { it.id }
            mAdapter.setList(res)
        }
        mViewModel.data.observe(viewLifecycleOwner, mObserver)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mRecyclerView.adapter = null
    }

    override fun onItemClick(position: Int, exercise: String) {

    }

}