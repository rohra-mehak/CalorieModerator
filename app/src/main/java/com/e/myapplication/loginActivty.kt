package com.e.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class loginActivty: AppCompatActivity() {


    private var mEmailView: AutoCompleteTextView? = null
    private var mPasswordView: EditText? = null
    private lateinit var mAuth: FirebaseAuth

    protected  override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mEmailView = findViewById(R.id.login_email) as AutoCompleteTextView
        mPasswordView = findViewById(R.id.login_passinput) as AutoCompleteTextView
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
        mAuth = FirebaseAuth.getInstance()
    }
    // Executed when Sign in button pressed
    fun signInExistingUser(v: View) {

        val intent = Intent(this, com.e.myapplication.FooddiaryActivity::class.java)
        finish()
        startActivity(intent)
        // TODO uncomment
        // attemptLogin()
    }
    // Executed when Register button pressed
    fun registerNewUser(v:View) {
        val intent = Intent(this, com.e.myapplication.registerActivity::class.java)
        finish()
        startActivity(intent)
    }

    private fun attemptLogin() {

        val email = mEmailView?.getText().toString()
        val password = mPasswordView?.getText().toString()
        if (email.equals("") || password.equals("")) return
        Toast.makeText(this, "User being logged in !", Toast.LENGTH_SHORT).show()

        mAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(this, OnCompleteListener {
                task ->
            Log.d("sucesss", "LogIn sucess" + task.isSuccessful)
            if (!task.isSuccessful) {
                Log.d("fail", "Login Fail" + task.exception)
                showAlert("Problem signing in , please try again later.")
            }
            else  {
                val intent = Intent(this, com.e.myapplication.MainActivity::class.java)
                finish()
                startActivity(intent)
            }

        })
        // TODO: Use FirebaseAuth to sign in with email & password
    }

    private fun showAlert( message : String){
        val builder = AlertDialog.Builder(this)
            .setTitle("Sorry")
            .setMessage(message)
            .setPositiveButton(android.R.string.ok,null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()


    }
}