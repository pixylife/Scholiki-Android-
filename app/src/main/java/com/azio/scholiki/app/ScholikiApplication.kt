package com.azio.scholiki.app

import android.app.Application
import com.azio.scholiki.dagger.AppComponent
import com.azio.scholiki.dagger.AppModule
import com.azio.scholiki.dagger.DaggerAppComponent

class ScholikiApplication : Application() {

    companion object {
        var globalVarFamilyId = ""
    }

    private val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent {
        return component
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}