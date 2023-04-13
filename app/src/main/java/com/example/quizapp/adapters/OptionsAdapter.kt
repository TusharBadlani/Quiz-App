package com.example.quizapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.models.Question

class OptionsAdapter (val context: Context, val question : Question) :
    RecyclerView.Adapter<OptionsAdapter.OptionViewHolder>() {

    private var options : List<String> = listOf(question.option1 , question.option2, question.option3, question.option4)

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<RadioButton>(R.id.quiz_option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener{
            question.userAnswer = options[position]
            notifyDataSetChanged()
        }

        //if(question.userAnswer == options[position])
    }

    override fun getItemCount(): Int {
        return options.size
    }
}

