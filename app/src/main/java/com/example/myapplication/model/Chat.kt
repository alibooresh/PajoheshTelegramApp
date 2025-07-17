package com.example.myapplication.model

data class Chat(
    val id: Int,
    val contactName: String,
    val lastMessage: String,
    val lastMessageTime: String,
    val unreadCount: Int = 0,
)
