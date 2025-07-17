package com.example.myapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun UserProfileScreen(
    phoneNumber: String,
    userName: String = "علی",
    userId: Long = 123456789L,
    bio: String = "این بایوگرافی من است.",
    username: String = "@aliuser",
    birthDate: String = "01/01/1370",
    onLogout: () -> Unit = {},
    onChatListClick: () -> Unit = {},
    onSessionsClick: () -> Unit = {},
    onSearchPhone: () -> Unit = {},
    onOpenContacts: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = userName,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = phoneNumber,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            InfoRow(label = "شناسه (ID):", value = userId.toString())
            InfoRow(label = "بایوگرافی:", value = bio)
            InfoRow(label = "شناسه کاربری:", value = username)
            InfoRow(label = "تاریخ تولد:", value = birthDate)

            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { onEditProfileClick() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ویرایش پروفایل")
            }
            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onOpenContacts,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("مشاهده مخاطبین")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onChatListClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("لیست چت ها")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onSearchPhone,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("استعلام حضور مخاطب")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onSessionsClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("لیست نشست های کاربر")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onLogout,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("خروج")
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    androidx.compose.foundation.layout.Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
