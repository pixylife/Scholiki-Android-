package com.azio.scholiki.ui.reset_password

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.azio.scholiki.R
import com.azio.scholiki.ui.BaseActivity
import com.azio.scholiki.ui.login.LoginActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_reset_password.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ResetPasswordActivity : BaseActivity<ResetPasswordContract.Presenter>(), ResetPasswordContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
    }

    override val presenter: ResetPasswordContract.Presenter
            by inject { parametersOf(this) }//inject ResetPasswordActivity using Koin

    override fun openLoginPage() {
        val intent = Intent(this@ResetPasswordActivity, LoginActivity::class.java)
        startActivity(intent)//start new activity
        finish()//finish current activity
    }


    /**
     * validate fields
     * @param email - email address from email edit text
     * return - Boolean
     */
    private fun isValidate(email: String): Boolean {

        //check field is empty or not
        if (TextUtils.isEmpty(email)) {
            return false
        }

        //Regex match for email address
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }

        return true
    }


    /**
     * return String error
     * @param email - email address from email edit text
     */
    private fun getMessage(email: String): String {

        if (TextUtils.isEmpty(email)) {
            return resources.getString(R.string.error_msg_emptyEmail)
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return resources.getString(R.string.error_msg_invalidEmail)
        }


        return "Something went wrong...!"
    }



    override fun showError(error: String) {
        Toasty.error(this, error, Toast.LENGTH_LONG, true).show()//show error toast message
    }

    override fun showLoading() {
        progress_view.visibility = View.VISIBLE //set visibility as visible
    }

    override fun hideLoading() {
        progress_view.visibility = View.VISIBLE //set visibility as visible
    }
}
