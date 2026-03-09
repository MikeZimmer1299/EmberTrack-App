package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cigarsandwhiskey.generalFunctions.DropdownMenu
import com.example.cigarsandwhiskey.generalFunctions.cigarBrandsList
import com.example.cigarsandwhiskey.generalFunctions.cigarOriginList
import com.example.cigarsandwhiskey.generalFunctions.ratingBar

import com.example.cigarsandwhiskey.objects.CigarReviews
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark


// Secondary screen for when the user wishes to create a new
//  review for a recently enjoyed cigar
@Composable
@Preview
fun NewCigarReview(){

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ) {
        // TIPS: This card holds the Cigar Brand, Name, and Country of Origin
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    45.dp,
                    10.dp, // right
                    0.dp
                )
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Row( // TIPS: Row for stating CIGAR BRAND
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Cigar Brand:",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        .drawBehind{
                            val strokeWidthPx = 3.dp.toPx()
                            val verticalOffset = size.height + 2.sp.toPx()
                            drawLine(
                                color = Color.Black,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))
//                 this ^^^ is possible going to replace the end padding in the text above

                // TIPS: mutableStateOf changed to mutableIntStateOf
                var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
                DropdownMenu(
                    cigarBrandsList(),
                    selectedIndex,
                    "Choose a Cigar Brand",
                    onItemClick = {selectedIndex = it}
                )
            }


            Row( // TIPS: Row for stating CIGAR NAME
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Cigar Name:",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp, 0.dp, 25.dp, 0.dp)
                        .drawBehind {
                            val strokeWidthPx = 3.dp.toPx()
                            val verticalOffset = size.height + 2.sp.toPx()
                            drawLine(
                                color = Color.Black,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        }
                )
                // TODO: Text Box to enter cigar name
            }


            Row( // TIPS: Row for stating COUNTRY OF ORIGIN
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Cigar Origin:",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp, 0.dp, 0.dp, 0.dp)
                        .drawBehind {
                            val strokeWidthPx = 3.dp.toPx()
                            val verticalOffset = size.height + 2.sp.toPx()
                            drawLine(
                                color = Color.Black,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        }
                )
                Spacer(modifier = Modifier.width(20.dp))

                // TODO: Dropdown menu of countries
                var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
                DropdownMenu(
                    cigarOriginList(),
                    selectedIndex,
                    "Country of Origin",
                    onItemClick = {selectedIndex = it}
                )
            }
        }



        // TODO: Card for length and ring gauge
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    15.dp,
                    10.dp, // right
                    0.dp
                )
                .size(width = 480.dp, height = 135.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ){
            Row( // Row for stating CIGAR LENGTH
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Cigar Length:",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp, 0.dp, 25.dp, 0.dp)
                        .drawBehind {
                            val strokeWidthPx = 3.dp.toPx()
                            val verticalOffset = size.height + 2.sp.toPx()
                            drawLine(
                                color = Color.Black,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        }
                )
                // TODO: Text box for cigar length
            }


            Row( // TIPS: Row for stating CIGAR RING GAUGE
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Ring Gauge:",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp, 0.dp, 25.dp, 0.dp)
                        .drawBehind {
                            val strokeWidthPx = 3.dp.toPx()
                            val verticalOffset = size.height + 2.sp.toPx()
                            drawLine(
                                color = Color.Black,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        }
                )
                // TODO: Text box for cigar length
            }
        }




        // TIPS: Score Sheet Begins Here
        val ratings = remember {
            mutableStateListOf(
                0f, // Draw
                0f, // Burn
                0f, // Construction
                0f, // Flavor
                0f, // Aroma
                0f, // Smoke Production
                0f  // Experience
            )
        }

        val finalScore by remember {
            derivedStateOf {
                ratings.average().toFloat()
            }
        }

        val categories = listOf(
            "Draw",
            "Burn",
            "Construction",
            "Flavor",
            "Aroma",
            "Smoke Production",
            "Experience"
        )

        categories.forEachIndexed { index, category ->
            ElevatedCard(
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        10.dp,
                        10.dp, // right
                        0.dp
                    )
//                .background(color = Color.Green)
                    .size(width = 480.dp, height = 160.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Row {
                    Text(
                        text = category,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(10.dp, 5.dp, 30.dp, 0.dp)
                            .drawBehind {
                                val strokeWidthPx = 3.dp.toPx()
                                val verticalOffset = size.height
                                drawLine(
                                    color = Color.Black,
                                    strokeWidth = strokeWidthPx,
                                    start = Offset(0f, verticalOffset),
                                    end = Offset(size.width, verticalOffset)
                                )
                            }
                    )
                }

                // Initiates the rating bar click-ability
                // TIPS: Allows each card to have its own rating bar that will
                //  not affect the others
//                var rating: Float by remember { mutableFloatStateOf(0f) }
                ratingBar(
                    rating = ratings[index],
                    onRatingChanged = { ratings[index] = it }
                )
            }
        }

        
        // TIPS: Card for the Overall Rating (Final Score)
        ElevatedCard(
            modifier = Modifier
                .padding(10.dp, // left
                    10.dp,
                    10.dp, // right
                    60.dp)
                .size(width = 480.dp, height = 80.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Row {
                Text(
                    text = "Final Score:",
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp, 5.dp, 0.dp, 0.dp)
                        .drawBehind{
                            val strokeWidthPx = 3.dp.toPx()
                            val verticalOffset = size.height
                            drawLine(
                                color = Color.Black,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        }
                )
                Text(
                    text = String.format("%.1f", finalScore),
                    fontSize = 70.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End, // TODO: Work on this text align
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 1.dp, 0.dp)
                )
            }
        }

        // TODO: Eventually, written review section (optional to user)



    }

}