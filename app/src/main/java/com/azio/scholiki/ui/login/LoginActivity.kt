package com.azio.scholiki.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import com.azio.scholiki.R
import com.azio.scholiki.ui.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : BaseActivity<LoginContract.Presenter>(),LoginContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if (isValidate(email, password)) {
                presenter.login(email,password)
            } else {
                showError(getMessage(email, password))
            }
        }

    }

    private fun isValidate(email: String, password: String): Boolean {
        if (TextUtils.isEmpty(email)) {
            return false
        }

        if (!EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }

        if (TextUtils.isEmpty(password)) {
            return false
        }

        return true
    }


    private fun getMessage(email: String, password: String): String {

        if (TextUtils.isEmpty(email)) {
            return resources.getString(R.string.error_msg_emptyEmail)
        }

        if (!EMAIL_ADDRESS.matcher(email).matches()) {
            return resources.getString(R.string.error_msg_invalidEmail)
        }

        if (TextUtils.isEmpty(password)) {
            return resources.getString(R.string.error_msg_emptyPassword)
        }

        return "Something went wrong...!"
    }



    override fun openHomePage(user: FirebaseUser) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val presenter: LoginContract.Presenter by inject { parametersOf(this) }


    override fun showError(error: String) {
        Toast.makeText(this,error,Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }




}
