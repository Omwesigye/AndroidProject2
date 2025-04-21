package com.example.bottonnav.pages

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoutinesPage() {
    val context = LocalContext.current
    val factory = remember { RoutineViewModelFactory(context.applicationContext as Application) }
    val viewModel: RoutineViewModel = viewModel(factory = factory)

    val routines by viewModel.routines

    // State for dialog
    val showDialog = remember { mutableStateOf(false) }
    val routineToEdit = remember { mutableStateOf<Routine?>(null) }

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
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(42.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingActionButton(
                    onClick = {
                        routineToEdit.value = null
                        showDialog.value = true
                    },
                    containerColor = Color.Blue
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Routine")
                }
            }
        }
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            if (routines.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🔄", fontSize = 40.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("No Routines!")
                        Text("Click the '+' button below to get started")
                    }
                }
            } else {
                LazyColumn {
                    items(routines) { routine ->
                        RoutineItem(
                            routine = routine,
                            onDelete = { viewModel.deleteRoutine(routine) },
                            onEdit = {
                                routineToEdit.value = routine
                                showDialog.value = true
                            }
                        )
                    }
                }
            }
        }
    }

    // Add/Edit Routine Dialog
    if (showDialog.value) {
        AddEditRoutineDialog(
            routine = routineToEdit.value,
            onSave = { routine ->
                viewModel.saveRoutine(routine)  // Save the routine to Room
                showDialog.value = false
            },
            onDismiss = { showDialog.value = false }
        )
    }
}

@Composable
fun RoutineItem(
    routine: Routine,
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = routine.name, style = MaterialTheme.typography.labelMedium)
                Text(text = "Timing: ${routine.time}", style = MaterialTheme.typography.labelMedium)
                Text(text = "Recurrence: ${routine.recurrence}", style = MaterialTheme.typography.labelMedium)
            }

            Column {
                IconButton(onClick = onEdit) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = onDelete) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}

@Composable
fun AddEditRoutineDialog(
    routine: Routine?,
    onSave: (Routine) -> Unit,
    onDismiss: () -> Unit
) {
    var taskName by remember { mutableStateOf(routine?.name ?: "") }
    var timing by remember { mutableStateOf(routine?.time ?: "") }
    var recurrence by remember { mutableStateOf(routine?.recurrence ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add/Edit Routine") },
        text = {
            Column {
                TextField(
                    value = taskName,
                    onValueChange = { taskName = it },
                    label = { Text("Task Name") }
                )
                TextField(
                    value = timing,
                    onValueChange = { timing = it },
                    label = { Text("Timing") }
                )
                TextField(
                    value = recurrence,
                    onValueChange = { recurrence = it },
                    label = { Text("Recurrence") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(Routine(id = routine?.id ?: 0, name = taskName, time = timing, recurrence = recurrence))
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
