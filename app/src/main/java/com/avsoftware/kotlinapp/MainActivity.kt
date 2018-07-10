package com.avsoftware.kotlinapp

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.avsoftware.kotlinapp.ui.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DashboardFragment.OnFragmentInteractionListener {

    //
    override fun onFragmentInteraction(uri: Uri) {
        Log.d("MainActivity", "Fragment Interaction: ${uri.toString()}")
    }

    lateinit var mNavController: NavController

    /**
     * Navigation Listener for Bottom Nav
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mNavController.navigate(R.id.homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                mNavController.navigate(R.id.dashboardFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                mNavController.navigate(R.id.cocktailFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNavController = findNavController(this, R.id.my_nav_host_fragment)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    // Navigation allow intercept of back button by navigation controller
    override fun onSupportNavigateUp()
            = findNavController(this, R.id.my_nav_host_fragment).navigateUp()
}
