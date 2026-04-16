package com.example.cigarsandwhiskey.objectInterface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.cigarsandwhiskey.AppDatabase
import com.example.cigarsandwhiskey.ui.theme.*


// TODO: Notes on key params
//  elevation: adds a shadow to the component that makes it appear elevated above the background
//  colors: uses the CardColors type to set default color of both container and any children
//  enabled: if you pass false for this param, card appears as disabled and does not respond
//  onClick: Card does not *usually* accept click events. This implementation is to overload

@Composable
fun HomeScreen(database: AppDatabase){

    // Allows access to the most recent cigar/whiskey review for the first elevated card
    val mostRecentCigarReview by remember(database) {
        database.cigarReviewDao().getMostRecentCigarReview()}.collectAsState(initial = null)
    val mostRecentWhiskeyReview by remember(database) {
        database.myWhiskeyReviewDao().getMostRecentWhiskeyReview()}.collectAsState(initial = null)


    // TODO: Within Card, since I plan to be able to scroll, I will need
    //  to use the `.verticalScroll(rememberScrollState())` modifier
    // https://developer.android.com/develop/ui/compose/touch-input/pointer-input/scroll
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 30.dp, 0.dp, 0.dp),
        ) {
            Text(text = "Home", fontSize = 40.sp,
                modifier = Modifier
                    .drawBehind{
                        val strokeWidthPx = 3.dp.toPx()
                        drawLine(
                            color = Color.Black,
                            strokeWidth = strokeWidthPx,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height)
                        )
                    },
                fontWeight = FontWeight.Bold
            )
        }

        /////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////

        // Row for each object desired on the home screen
        // TODO: May make these, instead of rows, as other cards? Example below of elevated card
        //  This is the correct move
        ElevatedCard(
//            onClick = {},
            modifier = Modifier
                .padding(
                    10.dp, // left
                    15.dp,
                    10.dp, // right
                    5.dp
                )
                .fillMaxWidth()
                .heightIn(200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        5.dp, // left
                        10.dp,
                        5.dp, // right
                        0.dp
                    )
            ){
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 225.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) {
                    mostRecentCigarReview?.let { review ->
                        Text(
                            text = review.brand,
                            fontSize = 33.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 5.dp)
                        )
                        Text(
                            text = review.cigarName,
                            fontSize = 33.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 0.dp)
                        )
                        Text(
                            text = "Score: ${"%.1f".format(review.finalScore)}",
                            fontSize = 33.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 5.dp)
                        )

                    } ?: Text(
                        text = "Time to add your first cigar review!",
                        fontSize = 33.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight.Bold,
                        softWrap = true,
                        modifier = Modifier.padding(10.dp, 10.dp),
                        textAlign = TextAlign.Center
                    )

                }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 225.dp, height = 180.dp)
                        .padding(
                            5.dp,
                            0.dp,
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) {
                    mostRecentWhiskeyReview?.let { review ->
                        Text(
                            text = review.brand,
                            fontSize = 33.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 5.dp)
                        )
                        Text(
                            text = review.whiskeyName,
                            fontSize = 33.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 0.dp)
                        )
                        Text(
                            text = "Score: ${review.overallScore}",
                            fontSize = 33.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 5.dp)
                        )

                    } ?: Text(
                        text = "Time to add your first whiskey review!",
                        fontSize = 33.sp,
                        lineHeight = 40.sp,
                        fontWeight = FontWeight.Bold,
                        softWrap = true,
                        modifier = Modifier.padding(10.dp, 10.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        ElevatedCard(
//            onClick = {},
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
                .fillMaxWidth()
                .heightIn(200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        5.dp, // left
                        10.dp,
                        5.dp, // right
                        0.dp
                    )
            ){
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
            }
        }

        ElevatedCard(
//            onClick = {},
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
                .fillMaxWidth()
                .heightIn(200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
//            Text(text = "This is where the list of the user's highest rated cigars is going")

            Row(
                modifier = Modifier
                    .padding(
                        5.dp, // left
                        10.dp,
                        5.dp, // right
                        0.dp
                    )
            ){
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 155.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
            }

        }

        ElevatedCard(
//            onClick = {},
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
                .fillMaxWidth()
                .heightIn(260.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Text(text = "This is potentially where a random whiskey/cigar brand will " +
                    "go, to give option for user to learn", color = Color.Black)
            // TODO: This text is currently the same color at "lushForestGrassLight"
            //  until I changed the color manually. Will need to keep in mind for future
        }


        ElevatedCard(modifier = Modifier
            .padding(20.dp)
            .height(20.dp)
        ) {
            // TIPS: Intentionally left blank. A terrible way to add spacing below the last
            //  card in the list. But it works, so ¯\_(ツ)_/¯
        }
    }

}