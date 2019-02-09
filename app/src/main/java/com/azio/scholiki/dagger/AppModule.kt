package com.azio.scholiki.dagger

import android.app.Application
import android.content.Context
import com.azio.scholiki.ui.BaseView
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module which provides all required dependencies about Context
 */
@Module
class AppModule(private var application: Application) {
    /**
     * Provides the Context
     * @return the Context to be provided
     */
    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return application
    }
}