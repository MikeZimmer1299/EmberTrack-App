package com.example.cigarsandwhiskey.objectInterface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.WhiskeyReviewDao
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark

@Composable
fun WhiskeyReviewsScreen(
    navController: NavController,
    whiskeyReviewDao: WhiskeyReviewDao
    ){

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ){
//        Text(text = "Welcome to the Whiskey Reviews Page")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 30.dp, 0.dp, 0.dp),

//        colors = CardDefaults.cardColors()
        ) {
            Text(text = "Whiskey Reviews", fontSize = 40.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .drawBehind{
                        val strokeWidthPx = 3.dp.toPx()
                        drawLine(
                            color = Color.Black,
                            strokeWidth = strokeWidthPx,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height)
                        )
                    }
            )
        }
    }

    // TODO: This button will allow a user to add a new whiskey review
    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(
                310.dp, // left
                920.dp,
                0.dp, // right
                0.dp
            ),
        containerColor = lushForestGrassLight,
        onClick = { navController.navigate("new_whiskey_review")},
        icon = { Icon(Icons.Filled.Edit, "Add Whiskey Review Button") },
        text = { Text(text = "Add Review")}
    )

}