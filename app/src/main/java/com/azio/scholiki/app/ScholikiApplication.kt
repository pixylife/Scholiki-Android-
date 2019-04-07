package com.azio.scholiki.app

import android.app.Application
import com.azio.scholiki.module.appModules
import es.dmoral.toasty.Toasty
import org.koin.standalone.StandAloneContext.startKoin

class ScholikiApplication : Application() {


    companion object {
        private lateinit var instance: ScholikiApplication
        fun get(): ScholikiApplication = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Toasty.Config.getInstance()
            .allowQueue(true) // optional (prevents several Toastys from queuing)
            .apply() // required

        // Start Koin
        startKoin(appModules)

    }
}