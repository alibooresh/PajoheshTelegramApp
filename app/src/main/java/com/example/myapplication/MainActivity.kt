package com.example.myapplication

import ContactListScreen
import EditProfileScreen
import PhoneNumberScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.model.Chat
import com.example.myapplication.model.Message
import com.example.myapplication.model.Session
import com.example.myapplication.ui.AddContactScreen
import com.example.myapplication.ui.ChatDetailScreen
import com.example.myapplication.ui.ChatListScreen
import com.example.myapplication.ui.ContactDetailsScreen
import com.example.myapplication.ui.MainScreen
import com.example.myapplication.ui.OtpVerificationScreen
import com.example.myapplication.ui.PhoneSearchScreen
import com.example.myapplication.ui.RegisterScreen
import com.example.myapplication.ui.SessionListScreen
import com.example.myapplication.ui.SplashScreen
import com.example.myapplication.ui.UserProfileScreen
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                var isEditing by remember { mutableStateOf(false) }
                val navController = rememberNavController()
                val sampleMessages = listOf(
                    Message(1, 1, "علی رضایی", "سلام! خوبی؟", "12:31", false),
                    Message(2, 1, "من", "سلام، خوبم ممنون!", "12:32", true),
                    Message(3, 2, "مینا حسینی", "فردا جلسه داریم", "دیروز", false),
                )
                val sampleChats = listOf(
                    Chat(1, "علی رضایی", "سلام چطوری؟", "12:30", 2),
                    Chat(3, "شاهین موسوی", "بفرست فایل رو لطفا", "9:15", 1),
                    Chat(2, "مینا حسینی", "فردا میری پادگان؟", "دیروز", 0),
                )
                val sampleSessions = listOf(
                    Session(
                        1,
                        "192.168.1.2",
                        "اندروید - گوشی",
                        "2025-07-01 10:00",
                        "2025-07-17 18:30"
                    ),
                    Session(2, "10.0.0.5", "وب - کروم", "2025-07-10 09:00", "2025-07-17 18:00"),
                    Session(
                        3,
                        "172.16.5.20",
                        "اندروید - تبلت",
                        "2025-07-15 08:30",
                        "2025-07-17 17:55"
                    ),
                )
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(navController)
                    }
                    composable("splash") {
                        SplashScreen(
                            onLoginClick = { -> navController.navigate("phone") },
                            onSignupClick = { -> navController.navigate("sign-up") }
                        )
                    }
                    composable("sign-up") {
                        RegisterScreen(onNext = { phone ->
                            navController.navigate("otp/${phone}")
                        })
                    }
                    composable("phone") {
                        PhoneNumberScreen(onNext = { phone ->
                            navController.navigate("otp/${phone}")
                        })
                    }

                    composable("otp/{phone}") { backStackEntry ->
                        val phone = backStackEntry.arguments?.getString("phone") ?: ""
                        OtpVerificationScreen(phone) {
                            navController.navigate("profile/${phone}")
                        }
                    }

                    composable("profile/{phone}") { backStackEntry ->
                        val phoneNumber = backStackEntry.arguments?.getString("phone") ?: ""
                        var isEditing by remember { mutableStateOf(false) }
                        var userName by remember { mutableStateOf("نام پیش‌فرض") }
                        var username by remember { mutableStateOf("username") }
                        var bio by remember { mutableStateOf("بیو") }
                        var userId = 1234L
                        var birthDate = "1370/01/01"

                        if (isEditing) {
                            EditProfileScreen(
                                initialName = userName,
                                initialUsername = username,
                                initialBio = bio,
                                onSave = { newName, newUsername, newBio ->
                                    userName = newName
                                    username = newUsername
                                    bio = newBio
                                    isEditing = false
                                },
                                onBack = { isEditing = false }
                            )
                        } else {
                            UserProfileScreen(
                                phoneNumber = phoneNumber,
                                userName = userName,
                                userId = userId,
                                bio = bio,
                                username = username,
                                birthDate = birthDate,
                                onLogout = { -> navController.navigate("phone") },
                                onEditProfileClick = { isEditing = true },
                                onOpenContacts = { -> navController.navigate("contacts") },
                                onSearchPhone = { -> navController.navigate("phone-search") },
                                onSessionsClick = { -> navController.navigate("sessions") },
                                onChatListClick = { -> navController.navigate("chat_list") }
                            )
                        }
                    }
                    composable("contacts") {
                        ContactListScreen(
                            onClickContact = { contact: Int ->
                                navController.navigate("contact_details/${contact}")
                            },
                            onAddContact = { -> navController.navigate("add_contact") })
                    }
                    composable("contact_details/{contact}") { backStackEntry ->
                        val contact = backStackEntry.arguments?.getString("contact")
                        if (contact != null) {
                            ContactDetailsScreen(contact.toInt())
                        }
                    }
                    composable("phone-search") {
                        PhoneSearchScreen(onSearch = { phone -> navController.navigate("phone-search-result/${phone}") })
                    }
                    composable("phone-search-result/{phone}") { backStackEntry ->
                        val phone = backStackEntry.arguments?.getString("phone") ?: ""

                    }
                    composable("chat_list") {
                        ChatListScreen(chats = sampleChats, onChatClick = { chatId ->
                            navController.navigate("chat_detail/$chatId")
                        })
                    }
                    composable("chat_detail/{chatId}") { backStackEntry ->
                        val chatId =
                            backStackEntry.arguments?.getString("chatId")?.toIntOrNull() ?: 0
                        val chat = sampleChats.find { it.id == chatId }
                        val messagesForChat = sampleMessages.filter { it.chatId == chatId }
                        if (chat != null) {
                            ChatDetailScreen(
                                chatId = chat.id,
                                chatName = chat.contactName,
                                messages = messagesForChat,
                                onSendMessage = { newMessage ->
                                    // اینجا می‌تونی منطق اضافه کردن پیام جدید به لیست پیام‌ها رو بزاری
                                    // فعلاً چون داریم فقط UI کار می‌کنیم، این بخش خالی می‌مونه یا می‌تونی Toast بزنی
                                }
                            )
                        }
                    }
                    composable("sessions") {
                        SessionListScreen(sessions = sampleSessions, onSessionClick = { session ->
                            println("Clicked session: ${session.ipAddress}")
                        })
                    }
                    composable("add_contact") {
                        AddContactScreen(
                            onSave = { name, phone ->
                                navController.popBackStack()
                            },
                            onCancel = {
                                navController.popBackStack()
                            }
                        )
                    }
                    composable("add_contact") {
                        AddContactScreen(
                            onSave = { name, phone ->
                                navController.popBackStack()
                            },
                            onCancel = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}