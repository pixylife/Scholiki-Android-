package com.azio.scholiki.ui.reset_password

import android.os.Bundle
import com.azio.scholiki.R
import com.azio.scholiki.ui.BaseActivity

class ResetPasswordActivity : BaseActivity<ResetPasswordContract.Presenter>(), ResetPasswordContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
    }

    override val presenter: ResetPasswordContract.Presenter
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun openLoginPage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
