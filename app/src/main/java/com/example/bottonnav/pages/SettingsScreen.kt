package com.example.bottonnav.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val settings = viewModel.settings
    var name by remember { mutableStateOf(settings.name) }
    var email by remember { mutableStateOf(settings.email) }
    var autoArm by remember { mutableStateOf(settings.autoArm) }
    var notifications by remember { mutableStateOf(settings.notifications) }
    var selectedColor by remember { mutableStateOf(settings.appColor) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "MY SMART HOME",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Yellow)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .background(selectedColor)
        ) {
            Text("User Settings", fontWeight = FontWeight.Bold)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Icon",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 8.dp)
                )

                Column(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            viewModel.updateSettings(settings.copy(name = name))
                        },
                        label = { Text("Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            viewModel.updateSettings(settings.copy(email = email))
                        },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("App Settings", fontWeight = FontWeight.Bold)

            Text("App Color:")
            Row {
                listOf(Color.Yellow, Color.Cyan, Color.Black).forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .background(color, shape = CircleShape)
                            .clickable {
                                selectedColor = color
                                viewModel.updateSettings(settings.copy(appColor = selectedColor))
                            }
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Auto Arm Security Alarm")
                Spacer(Modifier.weight(1f))
                Switch(
                    checked = autoArm,
                    onCheckedChange = {
                        autoArm = it
                        viewModel.updateSettings(settings.copy(autoArm = it))
                    }
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("App Notifications")
                Spacer(Modifier.weight(1f))
                Switch(
                    checked = notifications,
                    onCheckedChange = {
                        notifications = it
                        viewModel.updateSettings(settings.copy(notifications = it))
                    }
                )
            }

            Spacer(Modifier.height(16.dp))
            Text("Voice Assistants", fontWeight = FontWeight.Bold, color = Color.Gray)
            Text("Notifications & Permissions", fontWeight = FontWeight.Bold, color = Color.Gray)
        }
    }
}
