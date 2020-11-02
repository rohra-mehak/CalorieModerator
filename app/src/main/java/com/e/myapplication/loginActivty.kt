package com.e.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class loginActivty: AppCompatActivity() {


    private var mEmailView: AutoCompleteTextView? = null
    private var mPasswordView: EditText? = null
    protected  override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mEmailView = findViewById(R.id.login_email) as AutoCompleteTextView
        mPasswordView = findViewById(R.id.login_password) as EditText
        mPasswordView!!.setOnEditorActionListener(object: TextView.OnEditorActionListener {
            override fun onEditorAction(textView:TextView, id:Int, keyEvent: KeyEvent):Boolean {
                if (id == R.integer.login || id == EditorInfo.IME_NULL)
                {
                    attemptLogin()
                    return true
                }
                return false
            }
        })
        // TODO: Grab an instance of FirebaseAuth
    }
    // Executed when Sign in button pressed
    fun signInExistingUser(v: View) {
        // TODO: Call attemptLogin() here
    }
    // Executed when Register button pressed
    fun registerNewUser(v:View) {
        val intent = Intent(this, com.e.myapplication.registerActivity::class.java)
        finish()
        startActivity(intent)
    }
    // TODO: Complete the attemptLogin() method
    private fun attemptLogin() {
        // TODO: Use FirebaseAuth to sign in with email & password
    }
}