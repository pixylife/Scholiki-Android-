package com.azio.scholiki.model.datasource

import com.google.firebase.auth.FirebaseUser


/**
 * Created by Sahan Thinusha on 1/10/2019.
 */
interface UserDataSource : BaseDataSource{

    interface UserLoginCallback{
        fun onSuccess(user : FirebaseUser)
        fun onFailed(message : String)
    }

    fun userLogin(email : String, password : String, callback : UserDataSource.UserLoginCallback)
}