import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PhoneNumberScreen(onNext: (String) -> Unit) {
    var phoneNumber by remember { mutableStateOf("+98") }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("پژوهش گرام", fontSize = 36.sp, textAlign = TextAlign.Center)

            Spacer(modifier = Modifier.height(16.dp))

            Text("ورود با شماره تلفن", fontSize = 22.sp, textAlign = TextAlign.Right)

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("شماره تلفن", textAlign = TextAlign.Right) },
                    placeholder = { Text("+989123456789") },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(
                        textDirection = TextDirection.Ltr,
                        fontSize = 16.sp
                    ),
                    singleLine = true
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (phoneNumber.isNotBlank()) {
                        println(phoneNumber)
                        onNext(phoneNumber)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ادامه")
            }
        }
    }
}
