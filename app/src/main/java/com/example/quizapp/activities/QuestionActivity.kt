package com.example.quizapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.R
import com.example.quizapp.adapters.OptionsAdapter
import com.example.quizapp.models.Question
import com.example.quizapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity() {


    var quizzes : MutableList<Quiz>? = null
    var questions : MutableMap<String, Question>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        setUpFireStore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        previousBtn.setOnClickListener{
            index--
            bindViews()
        }
        nextBtn.setOnClickListener{
            index++
            bindViews()
        }
        btnSubmit.setOnClickListener{
            Log.d("FINALQUIZ", questions.toString())
        }
    }

    private fun setUpFireStore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if(date != null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }
    }

    private fun bindViews() {
        previousBtn.visibility =android.view.View.GONE
        nextBtn.visibility = android.view.View.GONE
        btnSubmit.visibility = android.view.View.GONE


        if(index == 1){
            nextBtn.visibility = android.view.View.VISIBLE
        }

        else if(index == questions!!.size){
            previousBtn.visibility = android.view.View.VISIBLE
            btnSubmit.visibility = android.view.View.VISIBLE
        }
        else{
            nextBtn.visibility = android.view.View.VISIBLE
            previousBtn.visibility = android.view.View.VISIBLE
        }

        val question = questions!!["question$index"]
        question?.let {
            description.text = it.description
            val optionsAdapter = OptionsAdapter(this, it)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionsAdapter
            optionList.setHasFixedSize(true)
        }
    }
}