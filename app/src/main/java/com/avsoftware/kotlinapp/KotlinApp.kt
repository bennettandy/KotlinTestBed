package com.avsoftware.kotlinapp

import android.app.Application
import com.avsoftware.data.dagger.DaggerDataComponent
import com.avsoftware.data.dagger.DataComponent
import com.avsoftware.kotlinapp.dagger.ApplicationComponent
import com.avsoftware.kotlinapp.dagger.DaggerApplicationComponent
import com.avsoftware.kotlinapp.dagger.modules.UIModule
import timber.log.Timber

class KotlinApp: Application(){

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: ApplicationComponent
    }
    override fun onCreate() {
        super.onCreate()

        // Data Module Application Component from Dagger
        val dataComponent: DataComponent = DaggerDataComponent.builder().build();

        // Dagger Application Component
        graph = DaggerApplicationComponent.builder()
                .dataComponent(dataComponent)
                .uIModule(UIModule()).build()
        graph.inject(this)

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