package com.example.entregapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        val buttonRetun: Button = findViewById(R.id.button2)
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
            if (editTextName.text == null || editTextName.text.isEmpty() ||
                editTextFirstSurname.text == null || editTextFirstSurname.text.isEmpty() ||
                editTextSecondSurname.text == null || editTextSecondSurname.text.isEmpty() ||
                editTextProgamacion.text == null || editTextProgamacion.text.isEmpty() ||
                editTextBases.text == null || editTextBases.text.isEmpty() ||
                editTextSistemas.text == null || editTextSistemas.text.isEmpty() ||
                editTextMarcas.text == null || editTextMarcas.text.isEmpty() ||
                editTextEntornos.text == null || editTextEntornos.text.isEmpty()
            ) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val name: String = editTextName.text.toString()
                val firstSurname: String = editTextFirstSurname.text.toString()
                val secondSurname: String = editTextSecondSurname.text.toString()
                val programacion: Int = editTextProgamacion.text.toString().toInt()
                val bases: Int = editTextBases.text.toString().toInt()
                val sistemas: Int = editTextSistemas.text.toString().toInt()
                val marcas: Int = editTextMarcas.text.toString().toInt()
                val entornos: Int = editTextEntornos.text.toString().toInt()

                val students: List<Student> = db.studentDao().getAll()

                if (!students.any { it.studentname == name && it.firstSurname == firstSurname && it.secondSurname == secondSurname }) {
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
                    editTextName.text.clear()
                    editTextFirstSurname.text.clear()
                    editTextSecondSurname.text.clear()
                    editTextProgamacion.text.clear()
                    editTextBases.text.clear()
                    editTextSistemas.text.clear()
                    editTextMarcas.text.clear()
                    editTextEntornos.text.clear()
                } else {
                    Toast.makeText(this, "Error, el estudiante ya tiene notas", Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }

        buttonRetun.setOnClickListener {
            finish()
        }
    }
}