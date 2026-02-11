package com.example.cigarsandwhiskey.objectInterface

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark

@Composable
@Preview
fun MyWhiskeyScreen(){

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ){
        Text(text = "Welcome to the My Whiskey Page")
    }

    // TODO: This will allow the user to add whiskey to their collection
    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(
                303.dp, // left
                920.dp,
                0.dp, // right
                0.dp
            ),
        containerColor = lushForestGrassLight,
        onClick = {},
        icon = { Icon(Icons.Filled.Edit, "Add Whiskey Button") },
        text = { Text(text = "Add Whiskey")}
    )

}