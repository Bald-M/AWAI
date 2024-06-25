package com.example.awai

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PromptActivity : AppCompatActivity() {

    private lateinit var selectedLanguage: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prompt)

        val age = findViewById<EditText>(R.id.ageET)
//        val language = findViewById<EditText>(R.id.languageET)
        val submitBtn = findViewById<Button>(R.id.confirmBtn)

        submitBtn.setOnClickListener {
            val ageText = age.text.toString()
//            val languageText = language.text.toString()
            val languageRadioGroup: RadioGroup = findViewById(R.id.languageRadioGroup)
            //var selectedLanguage = ""


            //selectedLanguage = "English"
            var selectedLanguage: String = when (languageRadioGroup.checkedRadioButtonId) {
                R.id.maoriRadioButton -> "Maori"
                else -> "English"
            }

            languageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.englishRadioButton -> {
                        // 用户选择了英语
                        selectedLanguage = "English"
                        Log.i("test",selectedLanguage)
                    }
                    R.id.maoriRadioButton -> {
                        // 用户选择了毛利语
                        selectedLanguage = "Maori"
                        Log.i("test",selectedLanguage)
                    }
                }
            }

            if (ageText.isEmpty() || selectedLanguage.isEmpty()) {
                Toast.makeText(this, "Input box is empty", Toast.LENGTH_SHORT).show()
            } else if (selectedLanguage == "English" || selectedLanguage == "Maori") {
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("age", ageText)
                    putExtra("language", selectedLanguage)
                }
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "You must only input English/Maori", Toast.LENGTH_SHORT).show()
            }
        }


    }
}