package com.uninet.wartarancage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.uninet.wartarancage.utils.SharedPreferences

class AccountActivity : AppCompatActivity() {
    lateinit var email : TextView
    lateinit var logout : MaterialButton
    lateinit var currentuser : FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        email = findViewById(R.id.email)
        logout = findViewById(R.id.logout__btn)
        currentuser = FirebaseAuth.getInstance().currentUser
        email.setText(currentuser.email.toString())


        Log.d("akun", "onCreate: "+currentuser.email.toString())

        logout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            SharedPreferences(this).isLogin = false
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
    }
}