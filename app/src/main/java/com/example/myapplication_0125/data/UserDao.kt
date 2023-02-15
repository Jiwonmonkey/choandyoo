package com.example.myapplication_0125.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    // 중복 확인 기능을 위한 아이디 검색 기능
    @Query("SELECT * FROM user_table WHERE userId = :email")
    fun getIdUser(email: String): LiveData<User>?

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAllUser()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUserAll(): LiveData<List<User>>
}