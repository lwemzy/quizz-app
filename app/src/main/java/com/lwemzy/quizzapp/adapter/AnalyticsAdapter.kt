package com.lwemzy.quizzapp;

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.lwemzy.quizzapp.model.UserResult

class AnalyticsAdapter(private val context: FragmentActivity, private val data: Array<UserResult>) :
    ArrayAdapter<UserResult>(context, R.layout.custom_analytics, data) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_analytics, null, true)

        val questionText = rowView.findViewById(R.id.questionT) as TextView
        val imageView = rowView.findViewById(R.id.grade) as ImageView
        val yourAnswerText = rowView.findViewById(R.id.providedAnswer) as TextView
        val correctAnswerText = rowView.findViewById(R.id.correctAnswer) as TextView

        var num: Int = position + 1
        questionText.text = "#" + num + ". " + data[position].question

        if (data[position].correct) {
            imageView.setBackgroundResource(R.drawable.correct)
        } else {
            imageView.setBackgroundResource(R.drawable.wrong)
        }

        yourAnswerText.text = "Your Answer: " + data[position].yourAnswer
        correctAnswerText.text = "Correct Answer: " + data[position].correctAnswer

        return rowView
    }
}