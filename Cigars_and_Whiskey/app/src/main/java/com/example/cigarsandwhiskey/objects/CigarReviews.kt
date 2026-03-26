package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable
import kotlin.math.exp
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
    var finalScore: Float = 0f
    // TODO: Add a slot for date
) {

//    @Composable
    fun setCigarReview(
        brand: String, cigarName: String, origin: String, sizeLength: String, ringGauge: String,
        draw: Int, burn: Int, construction: Int, flavors: Int, aroma: Int,
        smokeProduction: Int, experience: Int, finalScore: Float
    ){
        this.brand = brand
        this.cigarName = cigarName
        this.origin = origin
        this.sizeLength = sizeLength
        this.ringGauge = ringGauge
        this.draw = draw
        this.burn = burn
        this.construction = construction
        this.flavors = flavors
        this.aroma = aroma
        this.smokeProduction = smokeProduction
        this.experience = experience
//        this.finalScore = overallScore(draw, burn, construction, flavors, aroma, smokeProduction, experience)
        this.finalScore = finalScore
    }

    // TODO: This may actually be unnecessary, HOWEVER it could be useful if you want to delete
    //  a review later. Good to clear all memory before deleting an object.
    //  Then again, this is a garbage collector language, unlike C++ that requires that planning.
    fun resetCigarReview(){
        this.brand = ""
        this.cigarName = ""
        this.origin = ""
        this.sizeLength = ""
        this.ringGauge = ""
        this.draw = 0
        this.burn = 0
        this.construction = 0
        this.flavors = 0
        this.aroma = 0
        this.smokeProduction = 0
        this.experience = 0
        this.finalScore = 0f
    }


    // Calculates overall score of the cigar
    // Converts all int values into float value, to allow user to see the real score
    // TODO: this may be removed later, since the calculation is done in NewCigarReview.kt
//    @Composable
    fun overallScore(
        draw: Int, burn: Int, construction: Int, flavors: Int,
        aroma: Int, smokeProduction: Int, experience: Int
    ) : Float {
        val finalScore: Float = (
                draw + burn + construction + flavors + aroma
                        + smokeProduction + experience
                ).toFloat() / 7f
        return finalScore
    }

}