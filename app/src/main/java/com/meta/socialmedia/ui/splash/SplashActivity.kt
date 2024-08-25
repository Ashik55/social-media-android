package com.meta.socialmedia.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.meta.socialmedia.R
import com.meta.socialmedia.datasource.DatastoreManager
import com.meta.socialmedia.ui.auth.LoginActivity
import com.meta.socialmedia.ui.home.MainActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            initialLogic()
        }, 3500)


    }

    fun initialLogic() {
        val dataStoreManager = DatastoreManager(this)
        lifecycleScope.launch {
            val isLoggedIn = dataStoreManager.getBoolean("IS_LOGGED_IN", false).first()
            if (isLoggedIn == true)
                navigateToHome()
            else
                navigateToLoginActivity()
        }


    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}