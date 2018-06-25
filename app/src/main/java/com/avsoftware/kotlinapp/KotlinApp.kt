package com.avsoftware.kotlinapp

import android.app.Application
import timber.log.Timber

class KotlinApp: Application(){

    override fun onCreate() {
        super.onCreate()

        setUpLogging()
    }

    private fun setUpLogging() {
        // if (BuildConfig.DEBUG) {
        // always debug for development
        Timber.plant(Timber.DebugTree())
        //        } else {
        //            //Timber.plant(new CrashReportingTree());
        //        }
    }
}