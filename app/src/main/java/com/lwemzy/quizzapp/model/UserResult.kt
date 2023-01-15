package com.lwemzy.quizzapp.model

data class UserResult(
    var questionId: Int,
    var question: String,
    var yourAnswer: String,
    var correctAnswer: String,
    var correct: Boolean
)