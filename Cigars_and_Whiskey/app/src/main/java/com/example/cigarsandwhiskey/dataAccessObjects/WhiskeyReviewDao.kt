package com.example.cigarsandwhiskey.dataAccessObjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cigarsandwhiskey.objects.WhiskeyReviews
import kotlinx.coroutines.flow.Flow

@Dao
interface WhiskeyReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(reviews: WhiskeyReviews)

    @Query("SELECT * FROM whiskey_reviews ORDER BY id DESC")
    fun getAllWhiskeyReviews(): Flow<List<WhiskeyReviews>>

    @Query("SELECT * FROM whiskey_reviews WHERE whiskeyName = :name LIMIT 1")
    fun getAllWhiskeyReviews(name: String): WhiskeyReviews?

    @Delete
    suspend fun deleteReview(review: WhiskeyReviews)

}