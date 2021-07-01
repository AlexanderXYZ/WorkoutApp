package com.buslaev.workoutapp.screens.exercises

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.databinding.FragmentExercisesBinding
import com.buslaev.workoutapp.utilits.APP_ACTIVITY

class ExercisesFragment : Fragment(), ExercisesAdapter.OnItemClickListener {

    private var _binding: FragmentExercisesBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mAdapter: ExercisesAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mViewModel: ExercisesViewModel

    private lateinit var mObserver: Observer<List<ExerciseData>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExercisesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }


    private fun initialization() {
        mAdapter = ExercisesAdapter(this, APP_ACTIVITY)
        mRecyclerView = mBinding.exercisesRecyclerView
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        mViewModel = ViewModelProvider(this).get(ExercisesViewModel::class.java)

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
        val bundle = Bundle()
        bundle.putString("exercise", exercise)
        APP_ACTIVITY.navController.navigate(
            R.id.action_exercisesFragment_to_exerciseFragment,
            bundle
        )
    }

}