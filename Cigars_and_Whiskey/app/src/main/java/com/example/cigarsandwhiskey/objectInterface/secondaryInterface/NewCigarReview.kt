package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.cigarsandwhiskey.objects.CigarReviews




// New secondary screen for when the user wishes to create a new
//  review for a recently enjoyed cigar
@Composable
fun createCigarReview(){

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // TODO: ALL BARS WILL HAVE A LINE WITH HASH MARKS, FROM 0 TO 10
        //

        // TODO: Card for Cigar name and brand (double the content in this card)


        // TODO: Card for length and ring gauge


        // TODO: Card for Draw Rating


        // TODO: Card for Burn Rating


        // TODO: Card for Construction Rating


        // TODO: Card for Flavors Rating


        // TODO: Card for Aroma Rating


        // TODO: Card for Smoke Production Rating


        // TODO: Card for Experience Rating


        // TODO: Eventually, written review section (optional to user)



    }

}