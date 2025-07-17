package com.example.myapplication.model

data class Message(
    val id: Int,
    val chatId: Int,
    val senderName: String,
    val text: String,
    val timestamp: String,
    val isSentByUser: Boolean
)
