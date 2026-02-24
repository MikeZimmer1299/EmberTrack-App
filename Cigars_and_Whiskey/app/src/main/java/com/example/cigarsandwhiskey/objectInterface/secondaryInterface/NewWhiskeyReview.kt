package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


// TODO: New secondary screen for when the user wishes to create a new
//  review for a recently enjoyed whiskey
@Composable
fun NewWhiskeyReview(){

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // TODO: Card for Whiskey Name and Brand will be in same card


        // TODO: Card for Type and Proof will be in the same card


        // TODO: Card for Aromas


        // TODO: Card for Flavors


        // TODO: Card for Finish


    }

}