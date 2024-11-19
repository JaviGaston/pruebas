package com.example.entregapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.second_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second_activity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.button)
        val editTextName: EditText = findViewById(R.id.editTextTextName)
        val editTextFirstSurname: EditText = findViewById(R.id.editTextTextFirstSurname)
        val editTextSecondSurname: EditText = findViewById(R.id.editTextTextSecondSurname)
        val editTextProgamacion: EditText = findViewById(R.id.editTextTextProgramacion)
        val editTextBases: EditText = findViewById(R.id.editTextTextBases)
        val editTextSistemas: EditText = findViewById(R.id.editTextTextSistemas)
        val editTextMarcas: EditText = findViewById(R.id.editTextTextMarcas)
        val editTextEntornos: EditText = findViewById(R.id.editTextTextEntornos)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "AppDatabase"
        ).allowMainThreadQueries().build()

        button.setOnClickListener {
            var name: String = editTextName.text.toString()
            var firstSurname: String = editTextFirstSurname.text.toString()
            var secondSurname: String = editTextSecondSurname.text.toString()
            var programacion: Int = editTextProgamacion.text.toString().toInt()
            var bases: Int = editTextBases.text.toString().toInt()
            var sistemas: Int = editTextSistemas.text.toString().toInt()
            var marcas: Int = editTextMarcas.text.toString().toInt()
            var entornos: Int = editTextEntornos.text.toString().toInt()

            val student = Student(
                studentname = name,
                firstSurname = firstSurname,
                secondSurname = secondSurname,
                programacion = programacion,
                basesdedatos = bases,
                sistemas = sistemas,
                marcas = marcas,
                entornos = entornos
            )
            db.studentDao().insertAll(student)
        }
    }
}