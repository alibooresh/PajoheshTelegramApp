package com.example.myapplication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.myapplication.model.Chat
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val sampleChats = listOf(
        Chat(1, "علی رضایی", "سلام چطوری؟", "12:30", 2),
        Chat(3, "شاهین موسوی", "بفرست فایل رو لطفا", "9:15", 1),
        Chat(2, "مینا حسینی", "فردا میری پادگان؟", "دیروز", 0),
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onItemClicked = { route ->
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("PazhoheshGram") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            // محتوای صفحه فعلی
            Box(modifier = Modifier.padding(padding)) {
                // می‌تونی با Navigation اینجا صفحات مختلف رو نشون بدی
                ChatListScreen(chats = sampleChats, onChatClick = { chatId ->
                    navController.navigate("chat_detail/$chatId")
                })
            }
        }
    }
}
