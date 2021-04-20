package com.uninet.wartarancage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    lateinit var btnreg: AppCompatButton
    lateinit var btnlogin: TextView
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var etcPassword: EditText
    var valid : Boolean = false
    var TAG = "RegisterActivity"
    var a : String = "sdasda"

    lateinit var fAuth : FirebaseAuth
    lateinit var fStore : FirebaseFirestore
    lateinit var user : FirebaseUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        fAuth = FirebaseAuth.getInstance()
        fStore = FirebaseFirestore.getInstance()
        Log.d(TAG, "onCreate: (${a::class.simpleName}), datanya: $a")
        btnreg = this.findViewById(R.id.regbtn)
        btnlogin = this.findViewById(R.id.loginbtn)
        etEmail = this.findViewById(R.id.user__emailreg)
        etPassword = this.findViewById(R.id.user__passwordreg)
        etcPassword = this.findViewById(R.id.cuser__password)


        var email = etEmail.text
        var password = etPassword.text
        var cpassword = etcPassword.text


        btnlogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnreg.setOnClickListener {
            checkfield(etEmail)
            checkfield(etPassword)
            checkfield(etcPassword)
            if(valid){
//                var user : FirebaseUser = fAuth.currentUser
                fAuth.createUserWithEmailAndPassword(email.toString(), password.toString()).addOnCompleteListener(
                        OnCompleteListener {
                            if (it.isSuccessful) {

                                val firebaseUser: FirebaseUser = it.result!!.user!!
                                Toast.makeText(this, "sip", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, LoginActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                                finish()
                            }
                            else {
                                Toast.makeText(this, "Failed To Register,(${it.exception!!.message!!.toString()})", Toast.LENGTH_SHORT).show()
                            }

                        })
                        .addOnFailureListener(
                                OnFailureListener {
                                Toast.makeText(this, "Failure" + it.message, Toast.LENGTH_SHORT).show()
                })
            }


        }
    }


    fun checkfield(et: EditText) : Boolean {
        if (et.text.isEmpty()){
            et.setError("")
            valid = false
        }else{
            valid = true
        }
        return valid
    }


}

//.addOnSuccessListener(
//                                OnSuccessListener {
//                                    Toast.makeText(this, "Success create email", Toast.LENGTH_SHORT).show()
//                                    finish()
//                                    val doc: DocumentReference = fStore.collection("users").document(user.uid)
//
//                                    var userInfo: HashMap<String, Any> = HashMap()
//
//                                    userInfo.put("email", email.toString())
//                                    userInfo.put("password", email.toString())
//                                    //if admin
//                                    userInfo.put("isAdmin", "0")
//                                    doc.set(userInfo)
//                                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
//
//
//                })