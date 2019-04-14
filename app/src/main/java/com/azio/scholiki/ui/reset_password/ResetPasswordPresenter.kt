package com.azio.scholiki.ui.reset_password

import com.azio.scholiki.model.datasource.BaseDataSource
import com.azio.scholiki.model.datasource.impl.UserDataSourceImpl
import com.azio.scholiki.ui.BasePresenter
import org.koin.standalone.KoinComponent

/**
 * Created by Sahan Thinusha on 4/10/2019.
 */
class ResetPasswordPresenter(
    private val userDataSource: UserDataSourceImpl,
    override val view: ResetPasswordContract.View
) : BasePresenter<ResetPasswordContract.View>,
    ResetPasswordContract.Presenter,
    KoinComponent {


    override fun resetPassword(email: String) {
        view.showLoading()
        //calling data source function while listing into callback
        userDataSource.resetPassword(email, object : BaseDataSource.BaseCallBack {
            override fun onSuccess() {
                view.openLoginPage()
                view.hideLoading()
            }

            override fun onFailed(message: String) {
                view.hideLoading()
                view.showError(message)

            }
        })
    }

}