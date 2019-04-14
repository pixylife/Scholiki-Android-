package com.azio.scholiki.model.datasource.impl

import com.azio.scholiki.model.datasource.BaseDataSource
import com.azio.scholiki.model.datasource.UserDataSource
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by Sahan Thinusha on 1/10/2018.
 */
class UserDataSourceImpl(
    val auth: FirebaseAuth
)  : UserDataSource{

    /**
     * reset password function
     * Firebase send a email containing reset password instructions to given email(should be existing email on db{in Firebase auth})
     */
    override fun resetPassword(email: String, callback: BaseDataSource.BaseCallBack) {
        auth.sendPasswordResetEmail(email).addOnSuccessListener {
            //call success if task executed successfully
            callback.onSuccess()
        }.addOnFailureListener {
            callback.onFailed(it.message.toString())
        }
    }

    /**
     * user login function
     */
    override fun userLogin(email: String, password: String, callback: UserDataSource.UserLoginCallback) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    val user = auth.currentUser
                    //check user is null or not(just in case)
                    if(user!=null) {
                        callback.onSuccess(user)
                    }else{
                        callback.onFailed("Something went wrong")
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    callback.onFailed(task.exception?.localizedMessage.toString())
                }

            }    }


}