package com.example.entregapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        val buttonExit: Button = findViewById(R.id.buttonExit)
        val editText: EditText = findViewById(R.id.editTextUser)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword2)
        var user: String = editText.text.toString()
        var password: String = editTextPassword.text.toString()
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "AppDatabase"
        ).allowMainThreadQueries().build()
        buttonLogin.setOnClickListener {
            var userLogin:String = editText.text.toString()
            var passwordLogin:String = editTextPassword.text.toString()
            val user = User(userName = userLogin, password = passwordLogin)
            db.userDao().insertAll(user)
            Log.d("hola", "usuario introducido")
        }
    }
}