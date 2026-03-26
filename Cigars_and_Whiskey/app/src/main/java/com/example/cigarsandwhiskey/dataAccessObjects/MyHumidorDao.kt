package com.example.cigarsandwhiskey.dataAccessObjects

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

import com.example.cigarsandwhiskey.objects.HumidorWithCigars
import kotlinx.coroutines.flow.Flow

@Dao
interface MyHumidorDao {
    @Transaction // Crucial: tells Room to do two steps (fetch humidor + fetch cigars)
    @Query("SELECT * FROM my_humidors")
    fun getHumidorsWithCigars(): Flow<List<HumidorWithCigars>>
}