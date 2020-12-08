package com.e.myapplication

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.annotation.NonNull
//import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task

import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class registerActivity: AppCompatActivity() {
    // TODO: Add member variables here:
    // UI references.


    private var mEmailView: AutoCompleteTextView? = null
    private var mUsernameView: AutoCompleteTextView? = null
    private var mPasswordView: EditText? = null
    private var mConfirmPasswordView: EditText? = null

    // Firebase instance variables
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_register)



        mEmailView = findViewById(R.id.register_email) as AutoCompleteTextView
        mPasswordView = findViewById(R.id.register_password) as EditText
        mConfirmPasswordView = findViewById(R.id.register_confirm_password) as EditText
        mUsernameView = findViewById(R.id.register_username) as AutoCompleteTextView
        // Keyboard sign in action
        mConfirmPasswordView!!.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(textView: TextView, id: Int, keyEvent: KeyEvent): Boolean {
                if (id == R.integer.register_form_finished || id == EditorInfo.IME_NULL) {
                    attemptRegistration()
                    return true
                }
                return false
            }
        })
        mAuth = FirebaseAuth.getInstance()
        // TODO: Get hold of an instance of FirebaseAuth
    }

    // Executed when Sign Up button is pressed.
    fun signUp(v: View) {

        // Uncomment for test menu
        /*
        val intent = Intent(this, com.e.myapplication.TestActivity::class.java)
        finish()
        startActivity(intent)
        */
        attemptRegistration()
    }

    private fun attemptRegistration() {
        // Reset errors displayed in the form.
        mEmailView?.setError(null)
        mPasswordView?.setError(null)
        // Store values at the time of the login attempt.
        val email = mEmailView?.getText().toString()
        val password = mPasswordView?.getText().toString()
        var cancel = false
        var focusView: View? = null
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || !isPasswordValid(password)) {
            mPasswordView?.setError(getString(R.string.error_invalid_password))
            focusView = mPasswordView
            cancel = true
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView?.setError(getString(R.string.error_field_required))
            focusView = mEmailView
            cancel = true
        } else if (!isEmailValid(email)) {
            mEmailView?.setError(getString(R.string.error_invalid_email))
            focusView = mEmailView
            cancel = true
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            if (focusView != null) {
                focusView.requestFocus()
            }
        } else {
            CreateFirebaseUser()
            // TODO: Call create FirebaseUser() here
        }
    }

    private fun isEmailValid(email: String): Boolean {
        // You can add more checking logic here.
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        var confirmPassword = mConfirmPasswordView?.text.toString()
        return confirmPassword.equals(password) && password.length > 5
    }

    companion object {
        // Constants
        val CHAT_PREFS = "ChatPrefs"
        val DISPLAY_NAME_KEY = "username"
    }

    private fun CreateFirebaseUser(){
        val email = mEmailView?.getText().toString()
        // val email1 = mEmailView?.text.toString()
        val password = mPasswordView?.getText().toString()
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            Log.d("sucesss", "on complete" + task.isSuccessful)
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val intent = Intent(this, com.e.myapplication.DobActivity::class.java)
                intent.putExtra("USER_ID", user.uid)
                intent.putExtra("USER_USERNAME", mUsernameView?.text.toString())
                println(mUsernameView?.text.toString())
                finish()
                startActivity(intent)
            }

            if (!task.isSuccessful) {
                Log.d("fail", "user creation failed" + task.isSuccessful)
            }
        }
    }

    //start google sigh in  if button pressed
    fun GoogleNewUser(v: View) {
        val intent = Intent(this, com.e.myapplication.googleSignIn::class.java)
        finish()
        startActivity(intent)
    }


}

// TODO: Create a Firebase user

// TODO: Save the display name to Shared Preferences
/*fun SaveDisplayName() {
    val email = mEmailView?.getText().toString()
    // val email1 = mEmailView?.text.toString()
   var pref : SharedPreferences? = getSharedPreferences(CHAT_PREFS , 0)
    if (pref != null) {
        pref.edit().putString(  DISPLAY_NAME_KEY, ).apply()
    }
}*/

// TODO: Create an alert dialog to show in case registration failed