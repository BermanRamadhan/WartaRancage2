package com.uninet.wartarancage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.uninet.wartarancage.utils.SharedPreferences

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

//        var preferences = SharedPreferences(this)

        Handler().postDelayed({
            var intent: Intent
            intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
            finish()
        },1000)

    }
}