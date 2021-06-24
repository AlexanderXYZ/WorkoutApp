package com.buslaev.workoutapp.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.databinding.FragmentMainBinding
import com.buslaev.workoutapp.utilits.APP_ACTIVITY


class MainFragment : Fragment() {

    private var _binding:FragmentMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.supportActionBar?.show()
    }

}