package com.example.cigarsandwhiskey.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cigar_reviews")
data class CigarReviews(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0, // handled automatically by Room

    var brand: String = "", // this is a dropdown menu object
    var cigarName: String = "",
    var origin: String = "", // this is a dropdown menu object
    var sizeLength: String = "",
    var ringGauge: String = "",
    var draw: Int = 0,
    var burn: Int = 0,
    var construction: Int = 0,
    var flavors: Int = 0,
    var aroma: Int = 0,
    var smokeProduction: Int = 0,
    var experience: Int = 0,
    // Eventually, this will also have a written review object
    //  to attach to each review
    @ColumnInfo(defaultValue = "")
    var notes: String = "",
    var finalScore: Float = 0f
    // TODO: Add a slot for date
) {

}