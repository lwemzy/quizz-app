package com.lwemzy.quizzapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

private const val SPLASH_TIME_OUT = 2
var progressBar: ProgressBar? = null

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        progressBar = findViewById(R.id.ProgressBar)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}