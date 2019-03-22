package com.azio.scholiki.model.datasource.impl

import com.azio.scholiki.model.datasource.UserDataSource
import com.google.firebase.auth.FirebaseAuth

class UserDataSourceImpl(
    val auth: FirebaseAuth
)  : UserDataSource{

    override fun userLogin(email: String, password : String)  {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                }

            }
    }

}