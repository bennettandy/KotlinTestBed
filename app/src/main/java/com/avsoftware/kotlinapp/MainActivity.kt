package com.avsoftware.kotlinapp

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.avsoftware.kotlinapp.ui.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    /**
     * Navigation Listener for Bottom Nav
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                // not 100% sure what this labelled return means
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//        // set home fragment
//        supportFragmentManager.beginTransaction()
//                .replace(R.id.content_frame, HomeFragment.newInstance(), "Home")
//                .commit()

        doStuff()
    }

    private fun doStuff(){
        val bigDecimal = 100.bd()
        println("BigDecimal $bigDecimal")


    }
}

// extension function on Int
fun Int.bd(): BigDecimal = BigDecimal(this)
