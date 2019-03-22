package com.azio.scholiki.ui.login

import com.azio.scholiki.model.datasource.impl.UserDataSourceImpl
import com.azio.scholiki.ui.BasePresenter
import org.koin.standalone.KoinComponent

class LoginPresenter(
    private val userDataSource: UserDataSourceImpl, override val view: LoginContract.View
) : BasePresenter<LoginContract.View>, LoginContract.Presenter, KoinComponent {

    fun getUsers(){
        userDataSource.auth
    }

}