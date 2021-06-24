package com.buslaev.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.buslaev.workoutapp.databinding.ActivityMainBinding
import com.buslaev.workoutapp.utilits.APP_ACTIVITY
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.mikepenz.materialdrawer.util.setupWithNavController

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mDrawer: Drawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        APP_ACTIVITY = this

    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        setSupportActionBar(mToolbar)
        createDrawer()
    }

    private fun initFields() {
        mToolbar = mBinding.toolbar
        navController = Navigation.findNavController(this, R.id.nav_host)

    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder()
            .withActivity(APP_ACTIVITY)
            .withToolbar(mToolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Exercises")
                    .withSelectable(false),
                PrimaryDrawerItem().withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("Programs")
                    .withSelectable(false),
                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Nutrition")
                    .withSelectable(false),
                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Progress")
                    .withSelectable(false),
                DividerDrawerItem(),
                PrimaryDrawerItem().withIdentifier(104)
                    .withIconTintingEnabled(true)
                    .withName("Settings")
                    .withSelectable(false),
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener{
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when(position){
                        0 -> { APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_exercisesFragment) }
                        1 -> { APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_programsFragment) }
                        2 -> { APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_nutritionFragment) }
                        3 -> { APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_progressFragment) }
                        4 -> { APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_settingsFragment) }
                        else -> return false
                    }
                    return false
                }
            }).build()
    }


}