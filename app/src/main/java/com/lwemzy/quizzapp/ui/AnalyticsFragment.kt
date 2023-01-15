package com.lwemzy.quizzapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.lwemzy.quizzapp.AnalyticsAdapter
import com.lwemzy.quizzapp.R
import com.lwemzy.quizzapp.model.UserResult


class AnalyticsFragment : BaseFragment() {
    private lateinit var toHome: Button
    private lateinit var toQuestion: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_analytics, container, false)
        val gson = Gson()
        val spf = activity?.getSharedPreferences("results", Context.MODE_PRIVATE)
        val res = spf!!.getString("data", "")
        val list = gson.fromJson(res.toString(), Array<UserResult>::class.java).asList()
        val listView = root.findViewById<ListView>(R.id.listView)

        toHome = root.findViewById<Button>(R.id.btn_analysis_to_home)
        toQuestion = root.findViewById<Button>(R.id.btn_analysis_to_question)

        val data: Array<UserResult> = list.toTypedArray()
        val myAdapter = AnalyticsAdapter(requireActivity(), data)
        listView.adapter = myAdapter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toHome.setOnClickListener {

            val action = AnalyticsFragmentDirections.actionAnalyticsFragmentToHomeFragment()
            Navigation.findNavController(it).navigate(action)
        }
        toQuestion.setOnClickListener {

            val action = AnalyticsFragmentDirections.actionAnalyticsFragmentToQuestionFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
}