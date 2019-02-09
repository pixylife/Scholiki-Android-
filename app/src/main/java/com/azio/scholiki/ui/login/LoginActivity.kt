package com.azio.scholiki.ui.login

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Toast
import com.azio.scholiki.R
import com.azio.scholiki.dagger.AppComponent
import com.azio.scholiki.ui.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginPresenter,LoginView>() {


    override fun initDependencies(appComponent: AppComponent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getContext(): Context {
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

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            if (isValidate(email, password)) {
                signInWithEmailAndPassword(email,password)
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

    private fun showErrorMessage(message : String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }


    private fun signInWithEmailAndPassword(email : String ,password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Log.e("login","Success")
                } else {
                    // If sign in fails, display a message to the user.
                    showErrorMessage("Authentication Failed.")
                }

            }
    }


}
