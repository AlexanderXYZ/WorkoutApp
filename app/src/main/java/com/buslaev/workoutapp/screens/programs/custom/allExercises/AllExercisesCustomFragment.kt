package com.buslaev.workoutapp.screens.programs.custom.allExercises

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.databinding.FragmentAllExercisesBinding
import com.buslaev.workoutapp.screens.exercises.ExerciseData
import com.buslaev.workoutapp.utilits.APP_ACTIVITY
import com.google.gson.Gson


class AllExercisesCustomFragment : Fragment(), AllExercisesAdapter.OnItemClickListener {

    private var _binding: FragmentAllExercisesBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mViewModel: AllExercisesViewModel

    private lateinit var mAdapter: AllExercisesAdapter
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var mObserver: Observer<List<ExerciseData>>

    private var listExercises = mutableListOf<ExerciseData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllExercisesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        val exercise = arguments?.getString("ex") ?: ""
        mViewModel = ViewModelProvider(this).get(AllExercisesViewModel::class.java)
        mViewModel.setData(exercise)

        mAdapter = AllExercisesAdapter(APP_ACTIVITY, this)
        mRecyclerView = mBinding.allExercisesRecyclerView
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        mObserver = Observer { list ->
            mAdapter.setList(list)
        }
        mViewModel.data.observe(viewLifecycleOwner, mObserver)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.all_exercises_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_exercises_to_our_program -> {
                val bundle = Bundle()
                bundle.putString("list", Gson().toJson(listExercises))
                APP_ACTIVITY.navController.navigate(R.id.action_allExercisesFragment_to_customProgramFragment,bundle)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int, id: String, title: String, imageUrl: String) {
        val exercise = ExerciseData(id, title, imageUrl)
        listExercises.add(exercise)
    }
}