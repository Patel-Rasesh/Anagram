package com.example.anagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.MainThread
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // If they are already logged in, take them to MainActivity
        if (ParseUser.getCurrentUser() != null){
            gotoMainActivity()
        }
        findViewById<Button>(R.id.login).setOnClickListener{
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            loginUser(username, password)
        }
        findViewById<Button>(R.id.signUpBtn).setOnClickListener{
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            signUpUser(username, password)
        }
    }

    private fun signUpUser(username: String, password: String){
        // Create the ParseUser
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.
                Log.i(TAG, "Sign up successfully!")
                Toast.makeText(this, "Sign up successfully", Toast.LENGTH_SHORT).show()
                gotoMainActivity()
            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                Log.i(TAG, "Sign up failed!")
                Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loginUser(username: String, password: String){
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in")
                Toast.makeText(this, "We are in!", Toast.LENGTH_SHORT).show()
                gotoMainActivity()
            } else {
                // e comes from the call back function
                e.printStackTrace()
                Toast.makeText(this, "Error logging in!", Toast.LENGTH_SHORT).show()
                // Signup failed.  Look at the ParseException to see what happened.
            }})
        )
    }
    private fun gotoMainActivity(){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    companion object{
        const val TAG = "LoginActivity"
    }
}