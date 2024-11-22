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
import kotlin.system.exitProcess

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
        val buttonRegister: Button = findViewById(R.id.buttonRegister)
        val editText: EditText = findViewById(R.id.editTextUser)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "AppDatabase"
        ).allowMainThreadQueries().build()

        buttonLogin.setOnClickListener {
            val userLogin: String = editText.text.toString()
            val passwordLogin: String = editTextPassword.text.toString()
            val passwordDB : String = db.userDao().getPasswordByUserName(userLogin)
            if (validate(passwordDB,passwordLogin)){
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this, "Usuario o contraseña incorrectas", Toast.LENGTH_LONG
                ).show()
            }
        }

        buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonExit.setOnClickListener{
            exitProcess(0)
        }
    }
    fun validate(passwordDb: String, password: String): Boolean {
        val ret: Boolean
        if (password == passwordDb)
            ret = true
        else
            ret = false
        return ret
    }
}