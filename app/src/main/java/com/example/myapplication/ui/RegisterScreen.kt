package com.example.myapplication.ui

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(onNext: (String) -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("ثبت‌نام", fontSize = 24.sp)

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("نام") })
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("نام خانوادگی") })
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("یوزرنیم") })
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("شماره تلفن") })

        Spacer(modifier = Modifier.height(12.dp))

        // آپلود عکس (ساده - بدون picker واقعی برای MVP)
        Button(onClick = { /* باز کردن گالری یا انتخاب عکس */ }) {
            Text("انتخاب عکس پروفایل")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (phone.isNotBlank()) {
                    println(phone)
                    onNext(phone)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ادامه")
        }
    }
}
