package com.example.myapplication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun OtpVerificationScreen(
    phoneNumber: String,
    onVerified: () -> Unit
) {
    var otpCode by remember { mutableStateOf("") }
    val timer = remember { mutableStateOf(60) }

    LaunchedEffect(Unit) {
        while (timer.value > 0) {
            delay(1000L)
            timer.value = timer.value - 1
        }
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("کد تأیید برای شماره زیر ارسال شد!", fontSize = 20.sp)
            Text(
                phoneNumber,
                fontSize = 18.sp,
                textAlign = TextAlign.Left,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "ارسال مجدد تا ${timer.value} ثانیه",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = otpCode,
                onValueChange = { otpCode = it },
                label = { Text("کد تأیید") },
                placeholder = { Text("123456", textAlign = TextAlign.Left) },
                singleLine = true,
                textStyle = TextStyle(
                    textDirection = TextDirection.Ltr,
                    fontSize = 16.sp
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (otpCode.length == 6) {
                        // در دنیای واقعی: اینجا باید کد تایید بررسی بشه
                        onVerified()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("تأیید")
            }
        }
    }
}