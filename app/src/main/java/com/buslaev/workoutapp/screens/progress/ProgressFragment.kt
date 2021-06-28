package com.buslaev.workoutapp.screens.progress

import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import com.buslaev.workoutapp.R
import com.buslaev.workoutapp.databinding.FragmentProgressBinding
import com.buslaev.workoutapp.utilits.*


class ProgressFragment : Fragment() {

    private var _binding: FragmentProgressBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgressBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        getFromPreferences()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.progress_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_progress_menu -> {
                putIntoPreferences()
            }
            else -> false
        }
        return super.onOptionsItemSelected(item)
    }

    private fun putIntoPreferences() {
        val weight = mBinding.inputWeightEt.text.toString()
        val height = mBinding.inputHeightEt.text.toString()
        val fat = mBinding.inputFatEt.text.toString()
        val muscle = mBinding.inputMuscleEt.text.toString()
        val editor = PREFS.edit()
        editor.apply {
            putString(PREF_WEIGHT, weight)
            putString(PREF_HEIGHT, height)
            putString(PREF_FAT_PERC, fat)
            putString(PREF_MUSCLE_PERC, muscle)
        }.apply()
    }
    private fun getFromPreferences() {
        if (PREFS.contains(PREF_WEIGHT)){
            val weight = PREFS.getString(PREF_WEIGHT, "")
            mBinding.inputWeightEt.setText(weight)
        }
        if (PREFS.contains(PREF_HEIGHT)){
            val height = PREFS.getString(PREF_HEIGHT, "")
            mBinding.inputHeightEt.setText(height)
        }
        if (PREFS.contains(PREF_FAT_PERC)){
            val fat = PREFS.getString(PREF_FAT_PERC, "")
            mBinding.inputFatEt.setText(fat)
        }
        if (PREFS.contains(PREF_MUSCLE_PERC)){
            val muscle = PREFS.getString(PREF_MUSCLE_PERC, "")
            mBinding.inputMuscleEt.setText(muscle)
        }
    }
}