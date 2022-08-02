package com.gstech.taskcontroller.helper

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.gstech.taskcontroller.R

class FirebaseHelper {

    companion object {
        fun getDatabese() = FirebaseDatabase.getInstance().reference
        fun getAuth() = Firebase.auth
        fun getIdUser() = getAuth().uid
        fun isAuthentication() = getAuth().currentUser != null


        fun validError(error: String): Int {
            return when {

                error.contains("The email address is badly formatted") -> {
                    R.string.invalid_email_register_fragment
                }
                error.contains("There is no user record corresponding to this identifier. The user may have been deleted") -> {
                    R.string.account_not_registered_register_fragment
                }
                error.contains("The password is invalid or the user does not have a password") -> {
                    R.string.invalid_password_register_fragment
                }
                error.contains("We have blocked all requests from this device due to unusual activity. Try again later.") -> {
                    R.string.acess_temporarily_blocked
                }
                error.contains("The email address is already in use by another account") -> {
                    R.string.email_in_use_register_fragment
                }
                error.contains("The given password is invalid") -> {
                    R.string.password_should_little_caracters
                }
                else -> {
                    R.string.error_unexpected
                }

            }
        }
    }


}