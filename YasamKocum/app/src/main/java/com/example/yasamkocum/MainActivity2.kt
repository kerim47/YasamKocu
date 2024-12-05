package com.example.yasamkocum

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton


// Yasanilan durumlar:


class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        // val btnLogin = findViewById<Button>(R.id.btnLogin)
        // val btnSignup = findViewById<Button>(R.id.btnSignup)
        // val btnGoogleLogin = findViewById<Button>(R.id.btnGoogleLogin)
        val btnSignup = findViewById<TextView>(R.id.btnSignup)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        val btnGoogleLogin = findViewById<MaterialButton>(R.id.btnGoogleLogin)

        // Giriş Yap Butonu
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username == "admin" && password == "1234") { // Örnek giriş kontrolü
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Bu ekranı kapatır.
            } else {
                Toast.makeText(this, "Geçersiz kullanıcı adı veya şifre", Toast.LENGTH_SHORT).show()
            }
        }

        // Kayıt Ol Butonu
        btnSignup.setOnClickListener {
            Toast.makeText(this, "Kayıt Olma Ekranı Henüz Hazır Değil", Toast.LENGTH_SHORT).show()
        }

        // Google ile Giriş Yap Butonu
        btnGoogleLogin.setOnClickListener {
            Toast.makeText(this, "Google ile giriş henüz uygulanmadı", Toast.LENGTH_SHORT).show()
        }
    }
}
