package com.azio.scholiki.module

import com.azio.scholiki.model.datasource.impl.UserDataSourceImpl
import com.azio.scholiki.ui.login.LoginContract
import com.azio.scholiki.ui.login.LoginPresenter
import com.azio.scholiki.ui.reset_password.ResetPasswordContract
import com.azio.scholiki.ui.reset_password.ResetPasswordPresenter
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module.module

/**
 * Created by Sahan Thinusha on 2/15/2019.
 */
val DataModule = module {
    single ("auth") {FirebaseAuth.getInstance()}
    single ("UserDataSource"){ UserDataSourceImpl(get("auth")) }
}

val PagesModule = module {
    factory { (view: LoginContract.View) -> LoginPresenter(get("UserDataSource"),view) as LoginContract.Presenter }
    factory { (view: ResetPasswordContract.View) -> ResetPasswordPresenter(get("UserDataSource"),view) as ResetPasswordContract.Presenter }

}


val appModules = listOf(DataModule, PagesModule)
