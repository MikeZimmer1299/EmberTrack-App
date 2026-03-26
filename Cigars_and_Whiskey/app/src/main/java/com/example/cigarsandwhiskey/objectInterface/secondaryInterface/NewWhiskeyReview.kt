package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark


// TODO: New secondary screen for when the user wishes to create a new
//  review for a recently enjoyed whiskey
@Composable
fun NewWhiskeyReview(navController: NavController){

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ) {

    // TODO: Card for Whiskey Name and Brand will be in same card

        // TODO: Whiskey Brand (Dropdown menu)

        // TODO: Whiskey Name (Text Field)


    // TODO: Card for Type and Proof will be in the same card

        // TODO: Whiskey Type (Dropdown menu, i.e. bourbon, rye, Scotch, etc.)

        // TODO: Proof will be text field that will take integer

    // TODO: Card for Aromas


    // TODO: Card for Flavors


    // TODO: Card for Finish


    // TODO: Card for finishing the review and adding it to whiskey reviews list


    }

}