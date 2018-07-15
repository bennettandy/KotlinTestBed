package com.avsoftware.kotlinapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mNavController: NavController

    /**
     * Navigation Listener for Bottom Nav
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                mNavController.navigate(R.id.homeFragment)
                true
            }
            R.id.recipe_search -> {
                mNavController.navigate(R.id.recipeSearchFragment)
                true
            }
            R.id.navigation_dashboard -> {
                mNavController.navigate(R.id.dashboardFragment)
                true
            }
            R.id.navigation_notifications -> {
                mNavController.navigate(R.id.cocktailFragment)
                true
            }
            else -> false
        }
        //false
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
