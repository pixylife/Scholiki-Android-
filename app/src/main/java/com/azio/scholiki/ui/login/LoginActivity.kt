package com.azio.scholiki.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import com.azio.scholiki.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if (isValidate(email, password)) {



            } else {
                showErrorMessage(getMessage(email, password))
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

    private fun showErrorMessage(messsage : String){
        Toast.makeText(this,messsage,Toast.LENGTH_LONG).show()
    }


}
