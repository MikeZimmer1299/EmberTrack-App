package com.example.cigarsandwhiskey.specializedFunctions

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.cigarsandwhiskey.objects.CigarReviews
import com.example.cigarsandwhiskey.objects.WhiskeyReviews


// Checks if the cigar review is completed before allowing the review to be added
//  to the list of user reviews
//@Composable
fun cigarReviewCompletion(ReviewCheck: CigarReviews): Boolean{

    val textFields = listOf( // String comparison
        ReviewCheck.brand,
        ReviewCheck.cigarName,
        ReviewCheck.origin,
        ReviewCheck.sizeLength,
        ReviewCheck.ringGauge
    )
    val scoreFields = listOf( // Int comparison
        ReviewCheck.draw,
        ReviewCheck.burn,
        ReviewCheck.construction,
        ReviewCheck.flavors,
        ReviewCheck.aroma,
        ReviewCheck.smokeProduction,
        ReviewCheck.experience
    )

    // A review is complete if text fields are not blank and
    //  if scores are not 0
    return textFields.none { it.isBlank() } &&
            scoreFields.none { it == 0 }
}


// Checks if the whiskey review is completed before allowing the review to be added
//  to the list of user reviews
//@Composable
fun whiskeyReviewCompletion(ReviewCheck: WhiskeyReviews): Boolean{

    var reviewComplete: Boolean = false // default false, unless otherwise made true



    return reviewComplete
}