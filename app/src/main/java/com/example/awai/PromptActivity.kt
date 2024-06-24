package com.example.awai

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PromptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prompt)

        val age = findViewById<EditText>(R.id.ageET)
        val language = findViewById<EditText>(R.id.languageET)
        val submitBtn = findViewById<Button>(R.id.confirmBtn)

        submitBtn.setOnClickListener {
            val ageText = age.text.toString()
            val languageText = language.text.toString()

            if (ageText.isEmpty() || languageText.isEmpty()) {
                Toast.makeText(this, "Input box is empty", Toast.LENGTH_SHORT).show()
            } else if (languageText == "English" || languageText == "Maori") {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("age", ageText)
                    putExtra("language", languageText)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "You must only input English/Maori", Toast.LENGTH_SHORT).show()
            }
        }


    }
}