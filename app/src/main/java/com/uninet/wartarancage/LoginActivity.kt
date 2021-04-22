package com.uninet.wartarancage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.uninet.wartarancage.utils.SharedPreferences

class LoginActivity : AppCompatActivity() {
    lateinit var btnlogin: AppCompatButton
    lateinit var btnreg: TextView
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var sp : SharedPreferences
    lateinit var pb : ProgressBar
    var valid = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sp = SharedPreferences(this)
        btnlogin = this.findViewById(R.id.loginbutton)
        btnreg = this.findViewById(R.id.register_button)
        etEmail = this.findViewById(R.id.user__email)
        etPassword = this.findViewById(R.id.user__password)
        pb = findViewById(R.id.progressbar)


        btnlogin.setOnClickListener {
            var intent: Intent
            intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("url", "https://wartarancage.co/bewara")
            if (etEmail.text.toString() == "") {
                Toast.makeText(this, "email tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (etPassword.text.toString() == "") {
                Toast.makeText(this, "harap isi password", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmail.text.toString(),etPassword.text.toString()).addOnCompleteListener{
                    pb.setProgress(100)
                    pb.visibility = View.VISIBLE
                    if (it.isSuccessful){
                        startActivity(intent)
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        sp.isLogin = true
                        finish()

                    }
                    else{
                        Toast.makeText(this, ""+ it.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnreg.setOnClickListener {
            var p: Intent
            p = Intent(this, RegisterActivity::class.java)
            startActivity(p)
        }

    }


}