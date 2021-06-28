package com.buslaev.workoutapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.buslaev.workoutapp.data.initFirebase
import com.buslaev.workoutapp.databinding.ActivityMainBinding
import com.buslaev.workoutapp.utilits.APP_ACTIVITY
import com.buslaev.workoutapp.utilits.PREFS
import com.buslaev.workoutapp.utilits.PREF_SETTINGS

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var drawerLayout: DrawerLayout

    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        navController = findNavController(R.id.nav_host)
        drawerLayout = findViewById(R.id.drawer_layout)
        mBinding.navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.exercisesFragment) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.black)))
//                    }
                }
            }
        APP_ACTIVITY = this
        PREFS = getSharedPreferences(PREF_SETTINGS, Context.MODE_PRIVATE)

    }


    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}