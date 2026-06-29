package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.WhiskeyReviewDao
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark

@Composable
fun DisplayWhiskeyReview(
    navController: NavController,
    id: Int,
    whiskeyReviewDao: WhiskeyReviewDao
){
    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .verticalScroll(rememberScrollState()),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGreenDark
            )
        ) {
            // TIPS: Title for Screen
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 30.dp, 0.dp, 0.dp),
            ) {
                IconButton(
                    onClick = {navController.popBackStack()},
                    modifier = Modifier.padding(0.dp, 7.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back button",
                        modifier = Modifier.size(35.dp)
                    )
                }
                Text(
                    text = "Whiskey Review",
                    fontSize = dynamicFontSize * 1.4f,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .drawBehind {
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

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            val reviewToShow by remember(id){
                whiskeyReviewDao.getDisplayWhiskeyReview(id)}.collectAsState(initial = null)

            reviewToShow?.let { review ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(
                            10.dp, 0.dp, 10.dp, 0.dp
                        )
                        .fillMaxWidth()
                        .heightIn(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ) {
                    // TIPS: First card for Distillery and Name
                    Column(
                        modifier = Modifier.padding(0.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = review.brand,
                                fontSize = dynamicFontSize * 2f,
                                fontWeight = FontWeight.Bold,
                                lineHeight = dynamicFontSize * 1.55f,
                                textAlign = TextAlign.Center
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = review.whiskeyName,
                                fontSize = dynamicFontSize * 1.7f,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(0.dp, 0.dp),
                                lineHeight = dynamicFontSize * 1.4f,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                ///////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////

                // TIPS: Modifier for adding top/bottom lines
                val lineMod: Modifier = Modifier.weight(0.1f)
                    .drawBehind {
                        val strokeWidth = 4.dp.toPx()
                        drawLine( // top line
                            color = Color.Black,
                            start = Offset(0f, 0f),
                            end = Offset(screenWidth * 2.75f, 0f),
                            strokeWidth = strokeWidth
                        )
                        drawLine( // bottom line
                            color = Color.Black,
                            start = Offset(0f, size.height),
                            end = Offset(screenWidth * 2.75f, size.height),
                            strokeWidth = strokeWidth
                        )
                    }
                    .padding(5.dp, 1.dp)
                ///////////////////////////////////////////////////////////////////////////////

                // TIPS: Second card for Type, Origin, Aging, and Proof
                ElevatedCard(
                    modifier = Modifier
                        .padding(
                            10.dp, 10.dp, 10.dp, 0.dp
                        )
                        .fillMaxWidth()
                        .heightIn(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp,5.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = review.origin,
                                fontSize = dynamicFontSize * 1.2f,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(0.dp, 0.dp),
                                lineHeight = dynamicFontSize * 1.4f,
                                textAlign = TextAlign.Center
                            )
                        }
                        Row( // TIPS: Whiskey Type
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Box(
                                modifier = lineMod
                            ){
                                Text(
                                    text = "Type:",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = review.type,
                                fontSize = dynamicFontSize,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row( // TIPS: Whiskey Aging
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Box(
                                modifier = lineMod
                            ){
                                Text(
                                    text = "Aging:",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = review.ageStatement,
                                fontSize = dynamicFontSize,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row( // TIPS: Whiskey Proof
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Box(
                                modifier = lineMod
                            ){
                                Text(
                                    text = "Proof:",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = "${review.proof}/" + review.proof.toFloat() / 2 + "%",
                                fontSize = dynamicFontSize,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                ///////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////

                val underLine: Modifier = Modifier.drawBehind {
                    val strokeWidthPx = 3.dp.toPx()
                    val verticalOffset = size.height
                    drawLine(
                        color = Color.Black,
                        strokeWidth = strokeWidthPx,
                        start = Offset(0f, verticalOffset),
                        end = Offset(size.width, verticalOffset)
                    )
                }
                ///////////////////////////////////////////////////////////////////////////////

                // TODO: Separate cards for the text boxes for Flavor, Aroma, and Mouthfeel
                ElevatedCard( // TIPS Flavor
                    modifier = Modifier
                        .padding(
                            10.dp, 10.dp, 10.dp, 0.dp
                        )
                        .fillMaxWidth()
                        .heightIn(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Flavor",
                            fontSize = dynamicFontSize * 1.2f,
                            fontWeight = FontWeight.Bold,
                            modifier = underLine
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(10.dp, 5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(10.dp)
                        ){
                            Text(
                                text = review.flavors,
                                fontSize = 18.sp
                            )
                        }
                    }
                }

                ElevatedCard( // TIPS: Aroma
                    modifier = Modifier
                        .padding(
                            10.dp, 10.dp, 10.dp, 0.dp
                        )
                        .fillMaxWidth()
                        .heightIn(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Aroma",
                            fontSize = dynamicFontSize * 1.2f,
                            fontWeight = FontWeight.Bold,
                            modifier = underLine
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(10.dp, 5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(10.dp)
                        ){
                            Text(
                                text = review.aroma,
                                fontSize = 18.sp
                            )
                        }
                    }
                }

                ElevatedCard( // TIPS: Mouthfeel
                    modifier = Modifier
                        .padding(
                            10.dp, 10.dp, 10.dp, 0.dp
                        )
                        .fillMaxWidth()
                        .heightIn(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Mouthfeel",
                            fontSize = dynamicFontSize * 1.2f,
                            fontWeight = FontWeight.Bold,
                            modifier = underLine
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(10.dp, 5.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(10.dp)
                        ){
                            Text(
                                text = review.mouthFeel,
                                fontSize = 18.sp
                            )
                        }
                    }
                }

                ///////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////

                // TIPS: Overall Score Card
                ElevatedCard(
                    modifier = Modifier
                        .padding(
                            10.dp, // left
                            10.dp,
                            10.dp, // right
                            60.dp
                        )
                        .fillMaxWidth()
                        .heightIn(70.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ) {
                    Column{
                        Row{
                            Text(
                                text = "Final Score:",
                                fontSize = dynamicFontSize * 1.59f,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(10.dp, 5.dp, 0.dp, 0.dp)
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
                            Text(
                                text = review.overallScore.toString(),
                                fontSize = dynamicFontSize * 2f,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End, // TODO: Work on this text align
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 0.dp, 10.dp, 0.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}