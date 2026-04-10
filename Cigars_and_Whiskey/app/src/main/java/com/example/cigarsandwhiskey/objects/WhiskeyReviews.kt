package com.example.cigarsandwhiskey.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "whiskey_reviews")
data class WhiskeyReviews(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var brand: String = "",
    var whiskeyName: String = "",
    var type: String = "",
    var origin: String = "",
    var proof: String = "",
    var ageStatement: String = "",
    var flavors: String = "",
    var aroma: String = "",
    var mouthFeel: String = "",
    var overallScore: Int = 0
    // Eventually will have option to include written review.
    // This may be more directly necessary to have when the app
    //  goes live
) {



}