package com.example.myapplication_0125.data

import androidx.lifecycle.LiveData

// 앱에서 사용하는 데이터와 그 데이터 통신을 하는 역할
class UserRepository(private val userDao: UserDao) {

    val getUserAll: LiveData<List<User>> = userDao.getUserAll()

    fun getIdUser(email: String): LiveData<User>?{
        return userDao.getIdUser(email)
    }

    fun addUser(user: User){
        userDao.addUser(user)
    }

    fun deleteAllUser() {
        userDao.deleteAllUser()
    }
}