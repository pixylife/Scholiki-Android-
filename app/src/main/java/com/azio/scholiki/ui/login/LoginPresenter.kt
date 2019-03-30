package com.azio.scholiki.ui.login

import com.azio.scholiki.model.datasource.UserDataSource
import com.azio.scholiki.model.datasource.impl.UserDataSourceImpl
import com.azio.scholiki.ui.BasePresenter
import com.google.firebase.auth.FirebaseUser
import org.koin.standalone.KoinComponent


/**
 * Created by Sahan Thinusha on 1/12/2019.
 */

/**
 * Presenter class that handles the login business logic
 */
class LoginPresenter(
    private val userDataSource: UserDataSourceImpl, override val view: LoginContract.View
) : BasePresenter<LoginContract.View>, LoginContract.Presenter, KoinComponent {

    /**
     * Perform user login
     * @param email  - user email
     * @param password - user password
     * @return void - returns void but Asynchronously(after login success) view navigate into next page or show error messages
     */
    fun login(email : String, password : String){
        //calling data source function while listing into callback
        userDataSource.userLogin(email,password,object : UserDataSource.UserLoginCallback{
            override fun onSuccess(user: FirebaseUser) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailed(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }

}