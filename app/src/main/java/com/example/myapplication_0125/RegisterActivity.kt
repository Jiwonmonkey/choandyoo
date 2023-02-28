package com.example.myapplication_0125

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication_0125.data.User
import com.example.myapplication_0125.data.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel

    private var checkDuplicatedId = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val toolbar = findViewById<Toolbar>(R.id.back_login)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.short_left_arrow_16px)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // 회원가입 버튼 누르면 데이터 삽입 + 캘린더 화면으로 넘어가기
        RegisterButton.setOnClickListener {
            insertDataToDatabase()
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        // 휴지통 버튼 누르면 모든 데이터 삭제
        // user 속성 바꿀 때 테이블 비우기 용도로 만든 거라서 나중에 삭제할 것 ! !
        emptyButton.setOnClickListener {
            deleteAllDataToDatabase()
        }

        // 중복 확인 체크
        ConfirmId.setOnClickListener {
            checkExistDuplicatedId()
        }

    }
    private fun insertDataToDatabase() {

        val email = addEmail.text.toString()
        val pwd = addPwd.text.toString()
        val name = addName.text.toString()
        val phone = addPhone.text.toString()
        
        if(!checkDuplicatedId) {
            if(inputCheck(email, pwd, name, phone)){

                // Create User Object
                val user = User(0, email, pwd, name, phone)

                // Add Data to Database
                mUserViewModel.addUser(user)

                Toast.makeText(this@RegisterActivity, "Successfully added!", Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(this@RegisterActivity, "Please fill out add fields.", Toast.LENGTH_LONG).show()
            }
        }else {
            Toast.makeText(this@RegisterActivity, "Please check your new id is duplicated!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(email: String, pwd: String, name: String, phone: String): Boolean {
        return !(TextUtils.isEmpty(email) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(name) && TextUtils.isEmpty(phone))
    }

    // 데이터 전부 삭제하기
    private fun deleteAllDataToDatabase() {
        mUserViewModel.deleteAllUser()
        Toast.makeText(this@RegisterActivity, "Successfully deleted all data!", Toast.LENGTH_LONG).show()
    }

    // 아이디 존재 확인
    private fun checkExistDuplicatedId(): Boolean {


        val value = mUserViewModel.getIdUser(addEmail.text.toString())
        if(value != null) {
            checkDuplicatedId = true
            Toast.makeText(this@RegisterActivity, "already existed!", Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(this@RegisterActivity, "available id", Toast.LENGTH_LONG).show()

        }
        return checkDuplicatedId
    }
}

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        when (id) {
//            android.R.id.home -> {
//                finish()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//}