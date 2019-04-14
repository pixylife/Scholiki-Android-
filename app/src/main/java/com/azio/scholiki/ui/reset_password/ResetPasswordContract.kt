package com.azio.scholiki.ui.reset_password

import com.azio.scholiki.ui.BasePresenter
import com.azio.scholiki.ui.BaseView

/**
 * Created by Sahan Thinusha on 4/10/2019.
 */
interface ResetPasswordContract {
    interface View : BaseView {
        fun openLoginPage()
    }
    interface Presenter : BasePresenter<View> {
        fun resetPassword(email : String)
    }
}