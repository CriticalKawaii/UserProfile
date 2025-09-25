package com.kiryusha.userprofile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private lateinit var editTextUserName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupClickListener()
    }

    private fun initViews() {
        editTextUserName = findViewById(R.id.edit_text_userName)
        editTextEmail = findViewById(R.id.edit_text_email)
        buttonSave = findViewById(R.id.button_save)
    }

    private fun setupClickListener() {
        buttonSave.setOnClickListener {
            val userName = editTextUserName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()

            if (validateInput(userName, email)) {
                navigateToProfile(userName, email)
            }
        }
    }

    private fun validateInput(userName: String, email: String): Boolean {
        return when {
            userName.isEmpty() -> {
                showToast("Пожалуйста, введите имя")
                editTextUserName.requestFocus()
                false
            }
            email.isEmpty() -> {
                showToast("Пожалуйста, введите email")
                editTextEmail.requestFocus()
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                showToast("Пожалуйста, введите корректный email")
                editTextEmail.requestFocus()
                false
            }
            else -> true
        }
    }

    private fun navigateToProfile(userName: String, email: String) {
        val intent = Intent(this, ProfileActivity::class.java).apply {
            putExtra(ProfileActivity.EXTRA_USER_NAME, userName)
            putExtra(ProfileActivity.EXTRA_EMAIL, email)
        }
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}