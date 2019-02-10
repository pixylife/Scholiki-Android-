package com.azio.scholiki.dagger

import com.azio.scholiki.app.ScholikiApplication
import com.azio.scholiki.ui.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,FirebaseModule::class])
interface AppComponent {
   fun inject(app: ScholikiApplication)
   fun inject(target: LoginActivity)
}