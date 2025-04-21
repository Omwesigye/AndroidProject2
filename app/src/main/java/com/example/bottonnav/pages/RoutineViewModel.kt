package com.example.bottonnav.pages

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bottonnav.pages.Routine
import com.example.bottonnav.pages.RoutineDatabase
import kotlinx.coroutines.launch

class RoutineViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = RoutineDatabase.getDatabase(application).routineDao()

    var routines = mutableStateOf<List<Routine>>(emptyList())
        private set

    init {
        loadRoutines()
    }

    fun loadRoutines() {
        viewModelScope.launch {
            routines.value = dao.getAllRoutines()
        }
    }

    fun addRoutine(routine: Routine) {
        viewModelScope.launch {
            dao.insertRoutine(routine)
            loadRoutines()
        }
    }
    fun updateRoutine(routine: Routine) {
        viewModelScope.launch {
            dao.updateRoutine(routine)
            loadRoutines()
        }
    }

    fun deleteRoutine(routine: Routine) {
        viewModelScope.launch {
            dao.deleteRoutine(routine)
            loadRoutines()
        }
    }
    fun saveRoutine(routine: Routine) {
        viewModelScope.launch {
            if (routine.id == 0) {
                dao.insertRoutine(routine) // new routine
            } else {
                dao.updateRoutine(routine) // existing routine
            }
            loadRoutines()
        }
    }

}

