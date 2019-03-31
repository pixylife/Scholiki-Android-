package com.azio.scholiki.ui.login

import com.azio.scholiki.ui.BasePresenter
import com.azio.scholiki.ui.BaseView
import com.google.firebase.auth.FirebaseUser

interface LoginContract {
    interface View : BaseView{
        fun openHomePage(user : FirebaseUser)
    }
    interface Presenter : BasePresenter<View>{
        fun login(email : String, password : String)
    }
}