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

    /////////////////////////////////////////////////////////////////////////////
    // TIPS: This is for the filter function, which will return
    //  cigars by a specific name
    @Query("SELECT * FROM cigar_reviews WHERE cigarName = :name")
    fun getAllCigarNameReviews(name: String): Flow<List<CigarReviews?>>

    // TIPS: This is for the filter function, which will return
    //  cigars by a specific brand
    @Query("SELECT * FROM cigar_reviews WHERE brand = :brand")
    fun getAllBrandReviews(brand: String): Flow<List<CigarReviews?>>
    // Changed to List to view all reviews from a brand. I will need to implement
    //  an update to the screen to display reviews from the brand, not just all reviews

    // TIPS: This is for the filter function, which will return
    //  cigars from a specific country
    @Query("SELECT * FROM cigar_reviews WHERE origin = :origin")
    fun getAllCountryReviews(origin: String): Flow<List<CigarReviews?>>
    /////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////
    // TIPS: This is intended to be used on the HomeScreen. Same thing in WhiskeyReviewDao
    @Query("SELECT * FROM cigar_reviews ORDER BY id DESC LIMIT 1")
    fun getMostRecentCigarReview(): Flow<CigarReviews?>

    // TIPS: Query to retrieve specific cigar review to view. Well
    @Query("SELECT * FROM cigar_reviews WHERE id = :id LIMIT 1")
    fun getDisplayCigarReview(id: Int): Flow<CigarReviews?>
    /////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////
    @Delete
    suspend fun deleteReview(review: CigarReviews)
}