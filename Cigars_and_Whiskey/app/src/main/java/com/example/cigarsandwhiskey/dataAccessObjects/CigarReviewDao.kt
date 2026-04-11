package com.example.cigarsandwhiskey.dataAccessObjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cigarsandwhiskey.objects.CigarReviews
import kotlinx.coroutines.flow.Flow

@Dao
interface CigarReviewDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(reviews: CigarReviews)

    // TIPS: Returns all cigar reviews, which are output to display
    @Query("SELECT * FROM cigar_reviews ORDER BY id DESC")
    fun getAllCigarReviews(): Flow<List<CigarReviews>>

    // TIPS: This is for the search function, which will return
    //  cigars by a specific name
    @Query("SELECT * FROM cigar_reviews WHERE cigarName = :name LIMIT 1")
    fun getAllCigarNameReviews(name: String): CigarReviews?

    // TIPS: This is for the search function, which will return
    //  cigars by a specific brand
    @Query("SELECT * FROM cigar_reviews WHERE brand = :brand LIMIT 1")
    fun getAllBrandReviews(brand: String): CigarReviews?

    @Delete
    suspend fun deleteReview(review: CigarReviews)
}