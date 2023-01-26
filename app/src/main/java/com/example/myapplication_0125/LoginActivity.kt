package com.example.myapplication_0125

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //회원가입 화면으로 이동
        val moveBtn: Button = findViewById(R.id.move_btn)
        moveBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        //로그인 후 시작 화면으로 이동
        val loginBtn: Button = findViewById(R.id.login_button)
        loginBtn.setOnClickListener {

        }
    }
}