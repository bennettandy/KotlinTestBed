package com.avsoftware.kotlinapp

import android.app.Application
import com.avsoftware.data.dagger.DaggerDataComponent
import com.avsoftware.data.dagger.DataComponent
import com.avsoftware.kotlinapp.dagger.ApplicationComponent
import com.avsoftware.kotlinapp.dagger.DaggerApplicationComponent
import rx_activity_result2.RxActivityResult
import timber.log.Timber

class KotlinApp: Application(){

    companion object {
        //platformStatic allow access it from java code, hide setters
        @JvmStatic lateinit var graph: ApplicationComponent private set
        @JvmStatic lateinit var app: KotlinApp private set
    }

    override fun onCreate() {
        super.onCreate()

        app = this

        // logging framework
        Timber.plant(Timber.DebugTree())
        // Rx Activity Result handler
        RxActivityResult.register(this)

        // Data Module Application Component from Dagger
        val dataComponent: DataComponent = DaggerDataComponent.builder().build();

        // Dagger Application Component
        graph = DaggerApplicationComponent.builder()
                .dataComponent(dataComponent)
                .build()

        graph.inject(this)

    }
}