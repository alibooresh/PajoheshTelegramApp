package com.example.myapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.Session

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionListScreen(
    sessions: List<Session>,
    onSessionClick: (Session) -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("نشست‌های فعال") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(sessions) { session ->
                SessionItem(session = session, onClick = { onSessionClick(session) })
            }
        }
    }
}

@Composable
fun SessionItem(session: Session, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "آی‌پی: ${session.ipAddress}", style = MaterialTheme.typography.titleMedium)
            Text(
                text = "دستگاه: ${session.deviceType}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "تاریخ ورود: ${session.loginDate}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "آخرین بازدید: ${session.lastActiveDate}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
