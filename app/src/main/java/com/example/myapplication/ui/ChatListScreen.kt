package com.example.myapplication.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.myapplication.model.Chat

@Composable
fun ChatListScreen(
    chats: List<Chat>,
    onChatClick: (Int) -> Unit
) {
    LazyColumn {
        items(chats) { chat ->
            ChatListItem(chat = chat, onClick = onChatClick)
        }
    }
}
