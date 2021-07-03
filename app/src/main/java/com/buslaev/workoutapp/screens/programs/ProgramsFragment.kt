package com.buslaev.workoutapp.screens.programs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.databinding.FragmentProgramsBinding
import com.buslaev.workoutapp.roomData.OwnProgramData
import com.buslaev.workoutapp.utilits.APP_ACTIVITY


class ProgramsFragment : Fragment(), ProgramAdapter.OnItemClickListener {

    private var _binding: FragmentProgramsBinding? = null
    private val mBinding get() = _binding!!

    private lateinit var mOwnViewModel: ProgramsViewModel
    private lateinit var mOtherViewModel: OtherProgramsViewModel

    private lateinit var mAdapter: ProgramAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mObserver: Observer<List<OwnProgramData>>

    private var whichProgram = "own"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgramsBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        initSpinner()
        mOwnViewModel = ViewModelProvider(this).get(ProgramsViewModel::class.java)
        mOtherViewModel = ViewModelProvider(this).get(OtherProgramsViewModel::class.java)
        initRecyclerView()

        mBinding.addNewCustomProgramFab.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_programsFragment_to_customProgramFragment)
        }
    }

    private fun initSpinner() {
        val programs = arrayOf("own", "other")
        val spinnerAdapter =
            ArrayAdapter(APP_ACTIVITY, android.R.layout.simple_spinner_dropdown_item, programs)
        mBinding.programSpinner.adapter = spinnerAdapter
        mBinding.programSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    mRecyclerView.adapter = null
                    whichProgram = programs[p2]
                    initRecyclerView()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
    }

    private fun initRecyclerView() {
        mAdapter = ProgramAdapter(APP_ACTIVITY, this)
        mRecyclerView = mBinding.programsRecyclerView
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        mObserver = Observer { list ->
            val res = list.sortedBy { it.id }
            mAdapter.setList(res)
        }
        if (whichProgram == "own") {
            mOwnViewModel.data.observe(viewLifecycleOwner, mObserver)
        } else {
            mOtherViewModel.data.observe(viewLifecycleOwner, mObserver)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mRecyclerView.adapter = null
    }

    override fun onClick(position: Int, title: String, imageUrl: String, description: String) {

    }
}