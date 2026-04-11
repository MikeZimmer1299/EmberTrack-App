package com.example.cigarsandwhiskey.dataAccessObjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

import com.example.cigarsandwhiskey.objects.HumidorWithCigars
import com.example.cigarsandwhiskey.objects.MyHumidor
import kotlinx.coroutines.flow.Flow

@Dao
interface MyHumidorDao {
    @Transaction // Crucial: tells Room to do two steps (fetch humidor + fetch cigars)
    @Query("SELECT * FROM my_humidors")
    fun getHumidorsWithCigars(): Flow<List<HumidorWithCigars>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNewHumidor(humidor: MyHumidor)

    @Delete
    suspend fun deleteHumidor(humidor: MyHumidor)
}