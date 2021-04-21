package com.uninet.wartarancage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.uninet.wartarancage.utils.SharedPreferences

class AccountActivity : AppCompatActivity() {
    lateinit var email : TextView
    lateinit var logout : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        email = findViewById(R.id.email)
        logout = findViewById(R.id.logout__btn)


        logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            SharedPreferences(this).isLogin = false
            startActivity(Intent(this,MainActivity2::class.java))
        }
    }
}