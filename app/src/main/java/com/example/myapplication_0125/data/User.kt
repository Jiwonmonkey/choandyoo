package com.example.myapplication_0125.data

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User (
        @PrimaryKey(autoGenerate = true) val id: Int,
        val userId: String,
        val userPwd: String,
        val userName: String,
        val userPhoneNumber: String,
)
