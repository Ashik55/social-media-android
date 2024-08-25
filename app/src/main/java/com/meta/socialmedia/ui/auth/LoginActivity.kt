package com.meta.socialmedia.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.meta.socialmedia.R
import com.meta.socialmedia.datasource.DatastoreManager
import com.meta.socialmedia.ui.home.MainActivity
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEdittext)
        passwordEditText = findViewById(R.id.passwordEdittext)
        loginButton = findViewById(R.id.loginButton)


        val eyeIcon = findViewById<ImageView>(R.id.eyeIcon)

        eyeIcon.setOnClickListener {

            Log.d("INPUT", passwordEditText.inputType.toString())
            Log.d("TYPE_CLASS_TEXT", InputType.TYPE_CLASS_TEXT.toString())
            Log.d("TYPE_TEXT_VARIATION_PASSWORD", InputType.TYPE_TEXT_VARIATION_PASSWORD.toString())
            Log.d(
                "OR Block",
                (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD).toString()
            )

            if (passwordEditText.inputType ==
                (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            ) {
                passwordEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

                eyeIcon.setImageResource(R.drawable.ic_eye_off)
            } else {
//                passwordEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                eyeIcon.setImageResource(R.drawable.ic_eye)
            }
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show()
                }

                !isValidEmail(email) -> {
                    Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_LONG).show()
                }

                password.isEmpty() -> {
                    Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
                }

                else -> {
                    lifecycleScope.launch {
                        saveCredentials()
                    }
                    navigateToHome()
                }
            }


        }


    }

    suspend fun saveCredentials() {
        val dataStoreManager = DatastoreManager(this)
        dataStoreManager.saveBoolean("IS_LOGGED_IN", true)
    }

    fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}