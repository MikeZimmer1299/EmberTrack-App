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

    // TIPS: Returns all whiskey reviews, which are output to display
    @Query("SELECT * FROM whiskey_reviews ORDER BY id DESC")
    fun getAllWhiskeyReviews(): Flow<List<WhiskeyReviews>>

    /////////////////////////////////////////////////////////////////////////////
    // TIPS: This is for the search function, which will return
    //  whiskeys by a specific name
    @Query("SELECT * FROM whiskey_reviews WHERE whiskeyName = :name")
    fun getAllWhiskeyReviews(name: String): Flow<List<WhiskeyReviews?>>

    // TIPS: This is for the search function, which will return
    //  cigars by a specific brand. May need to change this to a list, so then
    //  all the cigars from a specific brand display
    @Query("SELECT * FROM whiskey_reviews WHERE brand = :brand")
    fun getAllWhiskeyBrandReviews(brand: String): Flow<List<WhiskeyReviews?>>

    // TIPS: This is for the search function, which will return
    //  whiskeys by a specific origin
    @Query("SELECT * FROM whiskey_reviews WHERE origin = :origin")
    fun getAllWhiskeyOriginReviews(origin: String): Flow<List<WhiskeyReviews?>>
    /////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////
    // TIPS: This is intended to be used on the HomeScreen
    @Query("SELECT * FROM whiskey_reviews ORDER BY id DESC LIMIT 1")
    fun getMostRecentWhiskeyReview(): Flow<WhiskeyReviews?> // Flow auto-updates

    // TIPS: Query to retrieve specific whiskey review to view.
    @Query("SELECT * FROM whiskey_reviews WHERE id = :id LIMIT 1")
    fun getDisplayWhiskeyReview(id: Int): Flow<WhiskeyReviews?>
    /////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////
    @Delete
    suspend fun deleteReview(review: WhiskeyReviews)
}