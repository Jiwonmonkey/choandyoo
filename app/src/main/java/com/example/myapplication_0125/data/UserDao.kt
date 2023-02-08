package com.example.myapplication_0125.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUserAll(): LiveData<List<User>>
}