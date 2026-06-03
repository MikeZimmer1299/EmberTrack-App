package com.example.cigarsandwhiskey.dataAccessObjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cigarsandwhiskey.objects.MyWhiskey
import kotlinx.coroutines.flow.Flow

@Dao
interface MyWhiskeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWhiskey(whiskey: MyWhiskey)

    // TIPS: Returns all cigar reviews, which are output to display
    @Query("SELECT * FROM my_whiskey ORDER BY id DESC")
    fun getAllWhiskey(): Flow<List<MyWhiskey>>

    // TIPS: This is for the search function, which will return
    //  cigars by a specific name
    @Query("SELECT * FROM my_whiskey WHERE name = :name LIMIT 1")
    fun getAllWhiskeyName(name: String): MyWhiskey?

    // TIPS: This is for the search function, which will return
    //  cigars by a specific brand
    @Query("SELECT * FROM my_whiskey WHERE brand = :brand LIMIT 1")
    fun getAllBrandWhiskey(brand: String): MyWhiskey?

    @Query("select * from my_whiskey ORDER BY id DESC LIMIT 3")
    fun getNewestAddedWhiskey(): Flow<List<MyWhiskey>>

    @Delete
    suspend fun deleteWhiskey(whiskey: MyWhiskey)
}