package com.example.cigarsandwhiskey.dataAccessObjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cigarsandwhiskey.objects.MyCigars
import kotlinx.coroutines.flow.Flow

@Dao
interface MyCigarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCigar(cigars: MyCigars)

    // TIPS Returns all cigars in collection
    @Query("SELECT * FROM my_cigars ORDER BY id DESC")
    fun getAllCigars(): Flow<List<MyCigars>>

    // TIPS: This is for the search function, which will return
    //  cigars by a specific name
    @Query("SELECT * FROM my_cigars WHERE cigarName = :name LIMIT 1")
    fun getAllCigars(name: String): MyCigars?

    // TIPS: This is for the search function, which will return
    //  cigars by a specific brand
    @Query("SELECT * FROM my_cigars WHERE cigarBrand = :brand LIMIT 1")
    fun getAllCigarsBrand(brand: String): MyCigars?

    @Delete
    suspend fun deleteCigar(cigar: MyCigars)

}