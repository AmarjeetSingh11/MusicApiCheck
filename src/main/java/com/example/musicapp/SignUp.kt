package com.example.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        val signupButton=findViewById<Button>(R.id.btnSignUp)
        val etName=findViewById<TextInputEditText>(R.id.etUsername)
        val etEmail=findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword=findViewById<TextInputEditText>(R.id.etPassword)

        signupButton.setOnClickListener {

            val name=etName.text.toString()
            val email=etEmail.text.toString()
            val password=etPassword.text.toString()

            val user=User(name,email,password)

            database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(name).setValue(user).addOnSuccessListener {

                etName.text?.clear()
                etEmail.text?.clear()
                etPassword.text?.clear()
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()

                val intent=Intent(this,MainScreen::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }




    }
}