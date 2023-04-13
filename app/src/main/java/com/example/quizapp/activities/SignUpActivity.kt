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

class SignUpActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var signUpEmailAddress: EditText
    lateinit var signUpPassword: EditText
    lateinit var confirmPassword: EditText
    lateinit var sigUpbtn : Button
    lateinit var bottomTextSignUp : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebaseAuth = FirebaseAuth.getInstance()

        signUpEmailAddress = findViewById(R.id.signUpEmailAddress)
        confirmPassword = findViewById(R.id.confirmPassword)
        signUpPassword = findViewById(R.id.signUpPassword)
        sigUpbtn = findViewById(R.id.sigUpbtn)
        bottomTextSignUp = findViewById(R.id.bottomTextSignUp)

        sigUpbtn.setOnClickListener{
            signUpUser()
        }

        bottomTextSignUp.setOnClickListener() {
            val intent = Intent(this, Login_screen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUpUser() {

        val email = signUpEmailAddress.text.toString()
        val password = signUpPassword.text.toString()
        val confirmation = confirmPassword.text.toString()

        if(email.isBlank() || password.isBlank() || confirmation.isBlank()){
            Toast.makeText(this,"Email or Password cannot be left blank",Toast.LENGTH_SHORT).show()
            return
        }

        if(password != confirmation){
            Toast.makeText(this,"Password and Confirm Password do not match",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Sign Up is Successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                        Toast.makeText(this, "Error Creating User",Toast.LENGTH_SHORT).show()
                }
            }

    }
}