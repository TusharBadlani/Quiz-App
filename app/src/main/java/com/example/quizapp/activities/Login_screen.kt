package com.example.quizapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class Login_screen : AppCompatActivity() {

    lateinit var loginEmailAddress : EditText
    lateinit var loginPassword:EditText
    lateinit var lgnButton : Button
    lateinit var bottomTextLogin:TextView

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        loginEmailAddress = findViewById(R.id.loginEmailAddress)
        loginPassword = findViewById(R.id.loginPassword)
        lgnButton = findViewById(R.id.lgnButton)
        bottomTextLogin = findViewById(R.id.bottomTextLogin)

        firebaseAuth = FirebaseAuth.getInstance()

        lgnButton.setOnClickListener(){
            login()
        }

        bottomTextLogin.setOnClickListener() {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login(){
        val email = loginEmailAddress.text.toString()
        val password = loginPassword.text.toString()

        if(email.isBlank() || password.isBlank()){
            Toast.makeText(this,"Email/Password cant be left empty",Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else {
                    Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                }
            }
    }
}