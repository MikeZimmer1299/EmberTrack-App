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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark
import com.example.cigarsandwhiskey.Navigation

@Composable
fun CigarReviewsScreen(navController: NavController){

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ){
        Text(text = "Welcome to the Cigar Reviews Page")
    }

    // TODO: This button will allow a user to add a new cigar review



    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(
                310.dp, // left
                920.dp,
                0.dp, // right
                0.dp
            ),
        containerColor = lushForestGrassLight,
        onClick = { navController.navigate("new_cigar_review")
//        {
//            popUpTo(navController.graph.startDestinationId){saveState = true}
//            launchSingleTop = true
//            restoreState = true
//        }
        },
        icon = { Icon(Icons.Filled.Edit, "Add Review Button") },
        text = { Text(text = "Add Review")}
    )

}