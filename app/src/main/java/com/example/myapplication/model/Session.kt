package com.example.myapplication.model

data class Session(
    val id: Int,
    val ipAddress: String,
    val deviceType: String,
    val loginDate: String,
    val lastActiveDate: String
)
