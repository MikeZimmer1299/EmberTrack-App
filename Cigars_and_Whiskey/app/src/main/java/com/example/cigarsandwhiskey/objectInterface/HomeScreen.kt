package com.example.cigarsandwhiskey.objectInterface

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.platform.LocalConfiguration
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

    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    // Allows access to the most recent cigar/whiskey review for the first elevated card
    val mostRecentCigarReview by remember(database) {
        database.cigarReviewDao().getMostRecentCigarReview()}.collectAsState(initial = null)
    val mostRecentWhiskeyReview by remember(database) {
        database.myWhiskeyReviewDao().getMostRecentWhiskeyReview()}.collectAsState(initial = null)

    // Allows access to most recently added cigars and whiskeys to collections
    val newestCigarsAdded by remember(database) {
        database.myCigarsDao().getNewestAddedCigars()}.collectAsState(initial = null)
    val newestWhiskeyAdded by remember(database) {
        database.myWhiskeyDao().getNewestAddedWhiskey()}.collectAsState(initial = null)


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
            Text(text = "Home", fontSize = dynamicFontSize * 1.4f,
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
                .heightIn(150.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Row(
                modifier = Modifier.padding(
                    15.dp,
                    5.dp,
                    0.dp,
                    0.dp,
                )
            ){
                Text(
                    text = "Most Recent Reviews",
                    fontSize = dynamicFontSize * 1.15f,
                    fontWeight = FontWeight.Bold,
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
                )
            }

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
                        .heightIn(130.dp)
                        .widthIn((screenWidth * .46f).dp)
                        .padding(
                            5.dp, // left
                            0.dp,
                            5.dp, // right
                            10.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) {
                    mostRecentCigarReview?.let { review ->
                        Text(
                            text = review.brand,
                            fontSize = 30.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 5.dp)
                        )
                        Text(
                            text = review.cigarName,
                            fontSize = 30.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 0.dp)
                        )
                        Text(
                            text = "Score: ${"%.1f".format(review.finalScore)}",
                            fontSize = 30.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 0.dp)
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
                        .heightIn(130.dp)
                        .widthIn((screenWidth * .4645f).dp)
                        .padding(
                            5.dp, // left
                            0.dp,
                            5.dp, // right
                            10.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) {
                    mostRecentWhiskeyReview?.let { review ->
                        Text(
                            text = review.brand,
                            fontSize = 30.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 5.dp)
                        )
                        Text(
                            text = review.whiskeyName,
                            fontSize = 30.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 0.dp)
                        )
                        Text(
                            text = "Score: ${review.overallScore}",
                            fontSize = 30.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 0.dp)
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
                .heightIn(150.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {

            Row(
                modifier = Modifier.padding(
                    15.dp,
                    5.dp,
                    0.dp,
                    0.dp,
                )
            ){
                Text(
                    text = "Newest Cigars",
                    fontSize = dynamicFontSize * 1.15f,
                    fontWeight = FontWeight.Bold,
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
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        5.dp, // left
                        10.dp,
                        5.dp, // right
                        10.dp
                    )
            ){

                if(newestCigarsAdded.isNullOrEmpty()) {
                    Card(
//                    onClick = {},
                        modifier = Modifier
                            .heightIn(90.dp)
                            .width((screenWidth * .35f).dp)
                            .padding(
                                5.dp, // left
                                0.dp,
                                5.dp, // right
                                0.dp
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = earthForestMediumDark
                        )
                    ) {
                        Text(
                            text = "Time to add your first cigars!",
                            fontSize = 20.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 10.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    newestCigarsAdded?.forEach { cigars ->
                        Card(
//                    onClick = {},
                            modifier = Modifier
                                .heightIn(9.dp)
                                .width((screenWidth * .31f).dp)
                                .padding(
                                    5.dp, // left
                                    0.dp,
                                    5.dp, // right
                                    0.dp
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = earthForestMediumDark
                            )
                        ) {
                            Text(
                                text = cigars?.cigarBrand ?: "Time to add your first cigars!",
                                fontSize = dynamicFontSize * .6f,
                                lineHeight = 30.sp,
                                fontWeight = FontWeight.Bold,
                                softWrap = true,
                                modifier = Modifier.padding(10.dp, 5.dp, end = 0.dp)
                            )
                            Text(
                                text = cigars?.cigarName ?: "",
                                fontSize = dynamicFontSize * .6f,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Bold,
                                softWrap = true,
                                modifier = Modifier.padding(10.dp, end = 10.dp, bottom = 0.dp)
                            )
                            Text(
                                text = "QTY: ${cigars?.quantity}" ?: "",
                                fontSize = dynamicFontSize * .6f,
                                lineHeight = 30.sp,
                                fontWeight = FontWeight.Bold,
                                softWrap = true,
                                modifier = Modifier.padding(10.dp, end = 10.dp, bottom = 2.dp)
                            )
                        }
                    }
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
                .heightIn(150.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Row(
                modifier = Modifier.padding(
                    15.dp,
                    5.dp,
                    0.dp,
                    0.dp,
                )
            ){
                Text(
                    text = "Newest Whiskeys",
                    fontSize = dynamicFontSize * 1.15f,
                    fontWeight = FontWeight.Bold,
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
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        5.dp, // left
                        10.dp,
                        5.dp, // right
                        10.dp
                    )
            ){
                if(newestWhiskeyAdded.isNullOrEmpty()){
                    Card(
//                    onClick = {},
                        modifier = Modifier
                            .heightIn(130.dp)
                            .width(180.dp)
                            .padding(
                                5.dp, // left
                                0.dp,
                                5.dp, // right
                                0.dp
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = earthForestMediumDark
                        )
                    ) {
                        Text(
                            text = "Time to add your first whiskey!",
                            fontSize = 30.sp,
                            lineHeight = 50.sp,
                            fontWeight = FontWeight.Bold,
                            softWrap = true,
                            modifier = Modifier.padding(10.dp, 10.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                } else {
                    newestWhiskeyAdded?.forEach { whiskey ->
                        Card(
//                    onClick = {},
                            modifier = Modifier
                                .heightIn(90.dp)
                                .width((screenWidth * .31f).dp)
                                .padding(
                                    5.dp, // left
                                    0.dp,
                                    5.dp, // right
                                    0.dp
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = earthForestMediumDark
                            )
                        ) {
                            Text(
                                text = whiskey.brand ?: "Time to add your first cigars!",
                                fontSize = dynamicFontSize * .6f,
                                lineHeight = 30.sp,
                                fontWeight = FontWeight.Bold,
                                softWrap = true,
                                modifier = Modifier.padding(10.dp, 5.dp, end = 0.dp)
                            )
                            Text(
                                text = whiskey.name ?: "",
                                fontSize = dynamicFontSize * .6f,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight.Bold,
                                softWrap = true,
                                modifier = Modifier.padding(10.dp, end = 10.dp, bottom = 0.dp)
                            )
                            Text(
                                text = "Proof: ${whiskey.proof}" ?: "",
                                fontSize = dynamicFontSize * .5f,
                                lineHeight = 25.sp,
                                fontWeight = FontWeight.Bold,
                                softWrap = true,
                                modifier = Modifier.padding(10.dp, end = 10.dp,  bottom = 5.dp)
                            )
                        }
                    }
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