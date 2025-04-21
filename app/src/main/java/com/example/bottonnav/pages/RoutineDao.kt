package com.example.bottonnav.pages
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoutineDao {
    @Query("SELECT * FROM routines")
    suspend fun getAllRoutines(): List<Routine>

    @Insert
    suspend fun insertRoutine(routine: Routine)
    @Update
    suspend fun updateRoutine(routine: Routine)

    @Delete
    suspend fun deleteRoutine(routine: Routine)

}

