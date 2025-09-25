package com.kiryusha.userprofile

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class ProfileActivity : ComponentActivity() {

    companion object {
        const val EXTRA_USER_NAME = "extra_user_name"
        const val EXTRA_EMAIL = "extra_email"
    }

    private lateinit var textViewUserName: TextView
    private lateinit var textViewEmail: TextView
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initViews()
        displayUserData()
        setupClickListener()
    }

    private fun initViews() {
        textViewUserName = findViewById(R.id.text_view_userName)
        textViewEmail = findViewById(R.id.text_view_email)
        buttonBack = findViewById(R.id.button_back)
    }

    private fun displayUserData() {
        val userName = intent.getStringExtra(EXTRA_USER_NAME) ?: "Не указано"
        val email = intent.getStringExtra(EXTRA_EMAIL) ?: "Не указано"

        textViewUserName.text = "Имя: $userName"
        textViewEmail.text = "Email: $email"
    }

    private fun setupClickListener() {
        buttonBack.setOnClickListener {
            finish() // Закрывает текущую активность и возвращается к предыдущей
        }
    }
}