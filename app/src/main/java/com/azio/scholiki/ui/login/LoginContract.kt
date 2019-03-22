package com.azio.scholiki.ui.login

import com.azio.scholiki.ui.BasePresenter
import com.azio.scholiki.ui.BaseView

interface LoginContract {
    interface View : BaseView
    interface Presenter : BasePresenter<View>
}