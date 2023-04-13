package com.example.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class IntroScreen : AppCompatActivity() {
    lateinit var getStarted: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_screen)
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            Toast.makeText(this, "User Exists and is already logged in", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        getStarted = findViewById(R.id.getStarted)

        getStarted.setOnClickListener() {
            redirect("LOGIN")
        }

    }

    private fun redirect(name: String) {

        val intent = when (name) {

            "LOGIN" -> Intent(this, Login_screen::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("wrong path destined")
        }
        startActivity(intent)
        finish()
    }
}