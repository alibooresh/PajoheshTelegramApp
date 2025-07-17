package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(onItemClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Text(
            text = "منوی اصلی",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        DrawerItem("پروفایل", Icons.Default.AccountCircle, "profile/{phone}", onItemClicked)
        DrawerItem("مخاطبین", Icons.Filled.AccountBox, "contacts", onItemClicked)
        DrawerItem("چت‌ها", Icons.Default.Face, "chat_list", onItemClicked)
        DrawerItem("تنظیمات", Icons.Default.Settings, "settings", onItemClicked)
        DrawerItem("استعلام شماره", Icons.Default.Search, "phone-search", onItemClicked)
        Spacer(modifier = Modifier.weight(1f))
        DrawerItem("خروج", Icons.Default.ExitToApp, "splash", onItemClicked)
    }
}

