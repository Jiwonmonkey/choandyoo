package com.example.myapplication_0125

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication_0125.data.User
import com.example.myapplication_0125.data.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val toolbar = findViewById<Toolbar>(R.id.back_login)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.short_left_arrow_16px)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        RegisterButton.setOnClickListener {
            insertDataToDatabase()
        }

    }

    private fun insertDataToDatabase() {

        val email = addEmail.text.toString()
        val pwd = addPwd.text.toString()
        val name = addName.text.toString()
        val phone = addPhone.text.toString()

        if(inputCheck(email, pwd, name, phone)){

            // Create User Object
            val user = User(0, email, pwd, name, phone)

            // Add Data to Database
            mUserViewModel.addUser(user)

            Toast.makeText(this@RegisterActivity, "Successfully added!", Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(this@RegisterActivity, "Please fill out add fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(email: String, pwd: String, name: String, phone: String): Boolean {
        return !(TextUtils.isEmpty(email) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(name) && TextUtils.isEmpty(phone))
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