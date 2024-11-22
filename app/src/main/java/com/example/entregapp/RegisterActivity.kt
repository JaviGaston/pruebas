package com.example.entregapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.register_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUser: EditText = findViewById(R.id.editTextText)
        val etPassword: EditText = findViewById(R.id.editTextText2)
        val buttonReturn: Button = findViewById(R.id.buttonReturn)
        val buttonRegister: Button = findViewById(R.id.buttonRegister)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "AppDatabase"
        ).allowMainThreadQueries().build()

        buttonReturn.setOnClickListener {
            finish()
        }

        buttonRegister.setOnClickListener {
            if(etUser.text == null || etUser.text.isEmpty())
                Toast.makeText(this, "Rellena el usuario", Toast.LENGTH_SHORT).show()
            else if (etPassword.text == null && etPassword.text.isEmpty())
                Toast.makeText(this, "Rellena el password", Toast.LENGTH_SHORT).show()
            else {
                val user = User(0,
                    userName = etUser.text.toString(),
                    password = etPassword.text.toString())
                    db.userDao().insertAll(user)
            }
        }

    }
}