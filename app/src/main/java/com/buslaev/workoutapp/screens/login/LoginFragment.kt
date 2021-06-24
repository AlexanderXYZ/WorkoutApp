package com.buslaev.workoutapp.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.databinding.FragmentLoginBinding
import com.buslaev.workoutapp.utilits.APP_ACTIVITY


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.supportActionBar?.hide()
    }
    override fun onStart() {
        super.onStart()
        initFields()

    }

    private fun initFields() {
        //Skip login screen
        mBinding.skipLoginTv.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

}