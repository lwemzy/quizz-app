package com.lwemzy.quizzapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.lwemzy.quizzapp.R
import com.lwemzy.quizzapp.model.UserResult
import com.lwemzy.quizzapp.repository.Answer
import com.lwemzy.quizzapp.repository.Question
import com.lwemzy.quizzapp.repository.QuizDatabase
import com.google.gson.Gson
import kotlinx.coroutines.launch

class QuestionFragment : BaseFragment() {
    private var nextBtn: Button? = null
    private var homeBtn: Button? = null
    private var radioG: RadioGroup? = null
    private var radioBtn1: RadioButton?=null
    private var radioBtn2: RadioButton?=null
    private var radioBtn3: RadioButton?=null
    private var txtView: TextView? = null
    private var questions: List<Question>?=null
    private var answers: List<Answer>?=null
    private var currentCorrect: String=""
    private var currentQuestion: Question?=null
    private var currentIndex: Int =0
    private var results =mutableListOf<UserResult>()
    private var  root: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_question, container, false)

        nextBtn = root?.findViewById(R.id.btn_next)
        homeBtn = root?.findViewById(R.id.btn_home)
        txtView = root?.findViewById(R.id.qtn)
        radioG = root?.findViewById(R.id.radioG)
        radioBtn1 = root?.findViewById(R.id.radiobtn1)
        radioBtn2 = root?.findViewById(R.id.radiobtn2)
        radioBtn3 = root?.findViewById(R.id.radiobtn3)

        return root
    }



    private fun displayQuestion(){
       if(questions !=null ){
           radioG?.clearCheck()

           if(currentIndex <= questions!!.size-1){
               val num = currentIndex+1
               val qtnId = questions?.get(currentIndex)?.id
               var count =0

               txtView?.text = num.toString() +". "+questions?.get(currentIndex)?.qtn
               currentQuestion= questions?.get(currentIndex)
               currentCorrect=""

               for(ans in answers!!){
                   if(ans.questionId == qtnId){
                       count += 1
                       if(ans.correct){
                           currentCorrect = ans.ans
                       }

                       if(count==1){
                           radioBtn1?.text = ans.ans
                       }

                       if(count==2){
                           radioBtn2?.text = ans.ans
                       }

                       if(count==3){
                           radioBtn3?.text = ans.ans
                       }
                   }
               }
           }
       }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
            }
        })

        nextBtn?.setOnClickListener {

            val checkedItemId: Int? = radioG?.checkedRadioButtonId
            if(checkedItemId ==-1){
                val toast : Toast =  Toast.makeText(context, "Please select your answer",Toast.LENGTH_LONG)
                toast.show()
            }else {

                if(currentIndex <= questions!!.size){
                    val checkedItemId: Int? = radioG?.checkedRadioButtonId
                    val selectedAnswerOption = root?.findViewById<RadioButton>(checkedItemId!!)
                    val selectedText = selectedAnswerOption!!.text.toString()
                    val currentQuestionText = currentQuestion!!.qtn
                    val isCorrect = selectedText == currentCorrect

                    val uResult = UserResult(currentQuestion!!.id,currentQuestionText,selectedText,currentCorrect,isCorrect)
                    results.add(uResult)


                }

                if(currentIndex>= questions!!.size-1){

                    val sharedPreference = activity?.getSharedPreferences("results", Context.MODE_PRIVATE)
                    val gson = Gson()
                    val resultsGson = gson.toJson(results)
                    val edit = sharedPreference!!.edit()
                    edit.putString("data", resultsGson)
                    edit.apply()

                    val action = QuestionFragmentDirections.actionQuestionFragmentToEndFragment()
                    Navigation.findNavController(it).navigate(action)
                }


                currentIndex++
                displayQuestion()
            }
        }
        homeBtn?.setOnClickListener {
            val action = QuestionFragmentDirections.actionQuestionFragmentToHomeFragment()
            Navigation.findNavController(it).navigate(action)
        }

        launch {
            context?.let{
                questions = QuizDatabase(it).getQuestionDao().getAllQuestions()
                answers = QuizDatabase(it).getAnswerDao().getAllAnswers()
                displayQuestion()

            }
        }
    }
}