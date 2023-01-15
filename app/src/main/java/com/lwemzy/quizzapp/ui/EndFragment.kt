package com.lwemzy.quizzapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.lwemzy.quizzapp.R
import com.lwemzy.quizzapp.model.UserResult


class EndFragment : BaseFragment() {
    private lateinit var retryBtn: Button
    private lateinit var analysisBtn: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_end, container, false)

        retryBtn = root.findViewById(R.id.btn_tryagain)
        analysisBtn = root.findViewById(R.id.btn_analysis)

        val totalTV = root.findViewById<TextView>(R.id.total)
        val correctCountTV = root.findViewById<TextView>(R.id.correctNumber)
        val wrongCountTV = root.findViewById<TextView>(R.id.wrongNumber)
        val scoreTV = root.findViewById<TextView>(R.id.score)
        val imgImage = root.findViewById<ImageView>(R.id.img)

        val gson = Gson()
        val spf = activity?.getSharedPreferences("results", Context.MODE_PRIVATE)
        val res = spf!!.getString("data", "")
        val list = gson.fromJson(res.toString(), Array<UserResult>::class.java).asList()

        var score = 0
        var correct = 0
        var wrong = 0
        var total = 0

        for (result in list) {
            total++
            if (result.correct) {
                correct++
                score++
            } else {
                wrong++
            }

        }
        totalTV.text = "Total Questions : $total"
        correctCountTV.text = "Correct Answers (Score) : $score"
        wrongCountTV.text = "Wrong Answers : $wrong"
        scoreTV.text = "Your Score is $score / $total"

        if (score > 9) {
            imgImage.setBackgroundResource(R.drawable.reportcard)
        } else {
            imgImage.setBackgroundResource(R.drawable.tryagain)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retryBtn.setOnClickListener {
            val action = EndFragmentDirections.actionEndFragmentToQuestionFragment()
            Navigation.findNavController(it).navigate(action)
        }

        analysisBtn.setOnClickListener {
            val action = EndFragmentDirections.actionEndFragmentToAnalyticsFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}