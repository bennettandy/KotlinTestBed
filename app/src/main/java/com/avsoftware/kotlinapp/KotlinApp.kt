package com.avsoftware.kotlinapp

import android.app.Application
import com.avsoftware.data.dagger.DataComponent
import com.avsoftware.kotlinapp.dagger.ApplicationComponent
import io.realm.Realm
import rx_activity_result2.RxActivityResult
import timber.log.Timber


class KotlinApp: Application() {

    companion object {
        //platformStatic allow access it from java code, hide setters
        @JvmStatic lateinit var graph: ApplicationComponent private set
        @JvmStatic lateinit var app: KotlinApp private set
    }

    override fun onCreate() {
        super.onCreate()

        app = this

        initRealm()

        // logging framework
        Timber.plant(Timber.DebugTree())
        // Rx Activity Result handler
        RxActivityResult.register(this)

        // Data Module Application Component from Dagger
        val dataComponent: DataComponent = DaggerDataComponent
                .builder()
                .build();

        // Dagger Application Component
        graph = DaggerApplicationComponent.builder()
                .dataComponent(dataComponent)
                .build()

        graph.inject(this)

    }

    private fun initRealm() : Unit {
        //Realm.init(this)
//        Stetho.initialize(Stetho.newInitializerBuilder(this)
//                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                .enableWebKitInspector(
//                        Realm.builder(this)
//                                .withDeleteIfMigrationNeeded(true) //if there is any changes in database schema then rebuild bd.
//                                .withMetaTables() //extract table meta data
//                                .withLimit(10000) //by default limit of data id 250, but you can increase with this
//                                .build()
//                )
//                .build())
    }
}