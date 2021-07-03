package com.buslaev.workoutapp.screens.programs.custom

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.databinding.FragmentCustomProgramBinding
import com.buslaev.workoutapp.roomData.OwnProgramData
import com.buslaev.workoutapp.screens.exercises.ExerciseData
import com.buslaev.workoutapp.screens.programs.custom.allExercises.AllExercisesAdapter
import com.buslaev.workoutapp.utilits.APP_ACTIVITY
import com.google.gson.Gson


class CustomProgramFragment : Fragment(), AllExercisesAdapter.OnItemClickListener {


    //HAS PROBLEM WITH INPUT DATA INTO DATABASE

    private var _binding: FragmentCustomProgramBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mViewModel: CustomProgramViewModel

    private lateinit var mAdapter: AllExercisesAdapter
    private lateinit var mRecyclerView: RecyclerView

    private var list = emptyList<ExerciseData>()
    private var choosedExercise: String = ""
    private val defaultImageUrl =
        "https://firebasestorage.googleapis.com/v0/b/workoutapp-cd5c0.appspot.com/o/cheast_exercise%2Fchest1.jpg?alt=media&token=425366cd-379e-42c6-8d1e-60703e0c63d0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomProgramBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mBinding.addNewExerciseToProgram.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("ex", choosedExercise)
            APP_ACTIVITY.navController.navigate(
                R.id.action_customProgramFragment_to_allExercisesFragment,
                bundle
            )
        }
        mAdapter = AllExercisesAdapter(APP_ACTIVITY, this)

        val arg = arguments?.getString("list")
        if (arg != null) {
            list = Gson().fromJson(arg, Array<ExerciseData>::class.java).toList()
        }

        initSpinner()

        mRecyclerView = mBinding.customProgramExercisesRecyclerView
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAdapter.setList(list)

        mViewModel = ViewModelProvider(this).get(CustomProgramViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.all_exercises_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_exercises_to_our_program -> {
                val title = mBinding.inputTitleEt.text.toString()
                if (title.isEmpty() || list.isEmpty()) {
                    Toast.makeText(
                        APP_ACTIVITY,
                        "Please write title and choose exercises",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val data =
                        OwnProgramData(title = title, imageUrl = defaultImageUrl, exercises = list)
                    mViewModel.addProgram(data) {
                        APP_ACTIVITY.navController.navigate(R.id.action_customProgramFragment_to_programsFragment)
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initSpinner() {
        val exercises = arrayOf("chest", "back")
        val spinnerAdapter =
            ArrayAdapter(APP_ACTIVITY, android.R.layout.simple_spinner_dropdown_item, exercises)
        mBinding.exercisesSpinner.adapter = spinnerAdapter
        mBinding.exercisesSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    choosedExercise = exercises[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mRecyclerView.adapter = null
    }

    override fun onClick(position: Int, id: String, title: String, imageUrl: String) {}
}