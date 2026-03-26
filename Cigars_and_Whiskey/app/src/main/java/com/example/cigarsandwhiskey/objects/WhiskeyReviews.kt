package com.example.cigarsandwhiskey.objects

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "whiskey_reviews")
class WhiskeyReviews(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var brand: String,
    var whiskeyName: String,
    var type: String,
    var proof: Int,
    var flavors: String,
    var aroma: String,
    // Eventually will have option to include written review
) {



}