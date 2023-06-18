package com.example.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnSignUp=findViewById<TextView>(R.id.etSignUp)
        btnSignUp.setOnClickListener {
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)

        }

         val etName=findViewById<TextInputEditText>(R.id.etUsername)
         val password=findViewById<TextInputEditText>(R.id.etPassword)
         val signIn=findViewById<Button>(R.id.btnSignIn)

        signIn.setOnClickListener {
            val name=etName.text.toString()
            val pass=password.text.toString()
            if (name.isNotEmpty() && pass.isNotEmpty()){

                readData(name,pass)
            }else{
                Toast.makeText(this,"Data cannot found",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(name: String, pass: String){

        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(name).get().addOnSuccessListener {

            if (it.exists() && (it.child("password").value == pass)){

                val intent=Intent(this,MainScreen::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Failed due to Server issue",Toast.LENGTH_SHORT).show()
        }

    }

}