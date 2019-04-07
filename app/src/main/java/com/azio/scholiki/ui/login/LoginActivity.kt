package com.azio.scholiki.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns.EMAIL_ADDRESS
import android.view.View
import android.widget.Toast
import com.azio.scholiki.R
import com.azio.scholiki.ui.BaseActivity
import com.azio.scholiki.ui.home.HomeActivity
import com.google.firebase.auth.FirebaseUser
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * This represents Login page ui
 */
class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //on click listener for login button
        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            //field validation
            if (isValidate(email, password)) {
                presenter.login(email, password)
            } else {
                showError(getMessage(email, password))
            }
        }

    }

    /**
     * validate fields
     * @param email - email address from email edit text
     * @param password - password field
     * return - Boolean
     */
    private fun isValidate(email: String, password: String): Boolean {

        //check field is empty or not
        if (TextUtils.isEmpty(email)) {
            return false
        }

         //Regex match for email address
        if (!EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }

        //check field is empty or not
        if (TextUtils.isEmpty(password)) {
            return false
        }

        return true
    }


    /**
     * return String error
     * @param email - email address from email edit text
     * @param password - password field
     */
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


    /**
     * open home page
     */
    override fun openHomePage(user: FirebaseUser) {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            .apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)//remove the LoginActivity from the BackStack.
            }

        startActivity(intent)//start new activity
        finish()//finish LoginActivity
    }

    override val presenter: LoginContract.Presenter by inject { parametersOf(this) }//inject LoginActivity using Koin


    override fun showError(error: String) {
        Toasty.error(this, error, Toast.LENGTH_LONG, true).show()//show error toast message
    }

    override fun showLoading() {
        progress_view.visibility = View.VISIBLE //set visibility as visible
    }

    override fun hideLoading() {
        progress_view.visibility = View.GONE //set visibility as gone
    }


}
