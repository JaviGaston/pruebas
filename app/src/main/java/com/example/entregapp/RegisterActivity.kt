package com.example.entregapp

import android.annotation.SuppressLint
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
    @SuppressLint("SuspiciousIndentation")
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonRegister.setOnClickListener {
            if(etUser.text == null || etUser.text.isEmpty() ||
                etPassword.text == null || etPassword.text.isEmpty())
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            else {
                val userName:String = etUser.text.toString()
                val password:String = etPassword.text.toString()
                val users:List<User> = db.userDao().getAll()
                if(!users.any{it.userName==userName}) {
                    val user = User(
                        0,
                        userName = userName,
                        password = password
                    )
                    db.userDao().insertAll(user)
                    Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("password",password)
                    intent.putExtra("userName",userName)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}