package com.e.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class loginActivty: AppCompatActivity() {


    private var mEmailView: AutoCompleteTextView? = null

    private var mPasswordView: EditText? = null
    private lateinit var mAuth: FirebaseAuth
    lateinit var signIn : Button
   // lateinit var  sp : SharedPreferences

    protected  override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionBar = supportActionBar
        actionBar!!.title = "Calorie Counter Login"
        mEmailView = findViewById(R.id.input_email_for_day) as AutoCompleteTextView
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

        signIn = findViewById<Button>(R.id.login_sign_in)
        
       /*// sp = getSharedPreferences("signIn" , MODE_PRIVATE)
        if(sp.getBoolean("logged",false)){
            val intent = Intent(this, com.e.myapplication.FooddiaryActivity::class.java)
            finish()
            startActivity(intent)
        }*/

//        signIn.setOnClickListener(View.OnClickListener {  val intent = Intent(this, com.e.myapplication.FooddiaryActivity::class.java)
//            finish()
//            startActivity(intent)
//            //sp.edit().putBoolean("logged",true).apply()})
//
//
    }


    // Executed when Sign in button pressed
    fun signInExistingUser(v: View) {
        //attemptLogin()
        val intent = Intent(this, com.e.myapplication.FooddiaryActivity::class.java)
        finish()
        startActivity(intent)
        // TODO uncomment
        attemptLogin()
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
                val intent = Intent(this, com.e.myapplication.FooddiaryActivity::class.java)
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