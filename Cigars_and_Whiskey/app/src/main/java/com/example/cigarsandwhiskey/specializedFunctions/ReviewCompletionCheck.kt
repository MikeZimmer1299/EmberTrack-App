package com.example.cigarsandwhiskey.specializedFunctions

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.cigarsandwhiskey.objects.CigarReviews
import com.example.cigarsandwhiskey.objects.MyCigars
import com.example.cigarsandwhiskey.objects.MyWhiskey
import com.example.cigarsandwhiskey.objects.WhiskeyReviews


// Checks if the cigar review is completed before allowing the review to be added
//  to the list of user reviews
fun cigarReviewCompletion(reviewCheck: CigarReviews): Boolean{

    val textFields = listOf( // String comparison
        reviewCheck.brand,
        reviewCheck.cigarName,
        reviewCheck.origin,
        reviewCheck.sizeLength,
        reviewCheck.ringGauge
    )
    val scoreFields = listOf( // Int comparison
        reviewCheck.draw,
        reviewCheck.burn,
        reviewCheck.construction,
        reviewCheck.flavors,
        reviewCheck.aroma,
        reviewCheck.smokeProduction,
        reviewCheck.experience
    )

    // A review is complete if text fields are not blank and
    //  if scores are not 0
    return textFields.none { it.isBlank() } &&
            scoreFields.none { it == 0 }
}


// Checks if the whiskey review is completed before allowing the review to be added
//  to the list of user reviews
fun whiskeyReviewCompletion(reviewCheck: WhiskeyReviews): Boolean{

    val textFields = listOf(
        reviewCheck.brand,
        reviewCheck.whiskeyName,
        reviewCheck.type,
        reviewCheck.origin,
        reviewCheck.proof,
        reviewCheck.ageStatement,
        reviewCheck.flavors,
        reviewCheck.aroma,
        reviewCheck.mouthFeel
    )
    val intField = listOf(reviewCheck.overallScore)

    return textFields.none { it.isBlank() } && intField.none { it == 0 }
}


// Checks if cigars being added to the collection is filled out
fun addNewCigarCompletion(additionCheck: MyCigars): Boolean{
    val textFields = listOf(
        additionCheck.cigarBrand,
        additionCheck.cigarName,
        additionCheck.countryOfOrigin,
        additionCheck.sizeLength,
        additionCheck.ringGauge
    )
    Log.d("Completion Check", "Completion")
    return textFields.none{it.isBlank()}
}


// Checks if whiskey being added to collection is filled out
fun addNewWhiskeyCompletion(additionCheck: MyWhiskey): Boolean{

    val textFields = listOf(
        additionCheck.brand,
        additionCheck.name,
        additionCheck.type,
        additionCheck.origin,
        additionCheck.ageStatement,
        additionCheck.proof,
        additionCheck.bottleSize
    )
    Log.d("Completion Check", "Completion")
    return textFields.none{it.isBlank()}
}