package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable
import kotlin.math.exp

class CigarReviews(
    var brand: String,
    var cigarName: String,
    var origin: String, // this will be a dropdown object, type probably needs to be changed
    var sizeLength: Int,
    var ringGauge: Int,
    var draw: Int,
    var burn: Int,
    var construction: Int,
    var flavors: Int,
    var aroma: Int,
    var smokeProduction: Int,
    var experience: Int,
    // Eventually, this will also have a written review object
    //  to attach to each review
    var finalScore: Float
) {

    @Composable
    fun setCigarReview(
        brand: String, cigarName: String, origin: String, sizeLength: Int, ringGauge: Int,
        draw: Int, burn: Int, construction: Int, flavors: Int, aroma: Int,
        smokeProduction: Int, experience: Int, finalScore: Int
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
        this.finalScore = overallScore(draw, burn, construction, flavors, aroma, smokeProduction, experience)
    }


    // Calculates overall score of the cigar
    // Converts all int values into float value, to allow user to see the real score
    @Composable
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