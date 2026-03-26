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

    @Query("SELECT * FROM cigar_reviews ORDER BY id DESC")
    fun getAllCigarReviews(): Flow<List<CigarReviews>>

    @Query("SELECT * FROM cigar_reviews WHERE cigarName = :name LIMIT 1")
    fun getAllCigarReviews(name: String): CigarReviews?

    @Delete
    suspend fun deleteReview(review: CigarReviews)
}