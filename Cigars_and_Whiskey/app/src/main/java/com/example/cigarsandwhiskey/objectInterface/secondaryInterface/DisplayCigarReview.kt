package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import android.util.Log
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
import com.example.cigarsandwhiskey.dataAccessObjects.CigarReviewDao
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark

@Composable
fun DisplayCigarReview(
    navController: NavController,
    id: Int,
    cigarReviewDao: CigarReviewDao
){
    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    Box(modifier = Modifier.fillMaxSize()){
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
                    text = "Cigar Review",
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

            val reviewToShow by remember(id)
            {cigarReviewDao.getDisplayCigarReview(id)}.collectAsState(initial = null)
            Log.d("Output", "cigarReviewDao.getDisplayCigarReview() success")

            // TODO: Now to figure out how to format the data to be visually pleasing.
            //  The order is going to be the same as new cigar reviews.
            //  The hard part is the design, similar but not copy-pasted

            reviewToShow?.let { review ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(
                            10.dp, 15.dp, 10.dp, 0.dp
                        )
                        .fillMaxWidth()
                        .heightIn(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = review.brand,
                                fontSize = dynamicFontSize * 1.6f,
                                fontWeight = FontWeight.Bold,
                                lineHeight = dynamicFontSize * 1.4f,
                                textAlign = TextAlign.Center
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = review.cigarName,
                                fontSize = dynamicFontSize * 1.4f,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(0.dp, 0.dp),
                                lineHeight = dynamicFontSize * 1.4f,
                                textAlign = TextAlign.Center
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
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
                            end = Offset(screenWidth * 2.7f, 0f),
                            strokeWidth = strokeWidth
                        )
                        drawLine( // bottom line
                            color = Color.Black,
                            start = Offset(0f, size.height),
                            end = Offset(screenWidth * 2.7f, size.height),
                            strokeWidth = strokeWidth
                        )
                    }
                    .padding(5.dp, 0.dp)
                ///////////////////////////////////////////////////////////////////////////////

                ElevatedCard(
                    modifier = Modifier
                        .padding(
                            10.dp, 15.dp, 10.dp, 0.dp
                        )
                        .fillMaxWidth()
                        .heightIn(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ) {
                    // Scores displayed
                    Column(
                        modifier = Modifier.padding(10.dp, 5.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = lineMod
                            ) {
                                Text(
                                    text = "Draw",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = review.draw.toString(),
                                fontSize = dynamicFontSize * 1.5f,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = lineMod
                            ) {
                                Text(
                                    text = "Burn",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = review.burn.toString(),
                                fontSize = dynamicFontSize * 1.5f,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = lineMod
                            ) {
                                Text(
                                    text = "Construction",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = review.construction.toString(),
                                fontSize = dynamicFontSize * 1.5f,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                ///////////////////////////////////////////////////////////////////////////////

                ElevatedCard(
                    modifier = Modifier
                        .padding(
                            10.dp, 15.dp, 10.dp, 0.dp
                        )
                        .fillMaxWidth()
                        .heightIn(100.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp, 5.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = lineMod
                            ) {
                                Text(
                                    text = "Flavor",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = review.flavors.toString(),
                                fontSize = dynamicFontSize * 1.5f,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = lineMod
                            ) {
                                Text(
                                    text = "Aroma",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = review.aroma.toString(),
                                fontSize = dynamicFontSize * 1.5f,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                          modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = lineMod
                            ) {
                                Text(
                                    text = "Smoke",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = review.smokeProduction.toString(),
                                fontSize = dynamicFontSize * 1.5f,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 0.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = lineMod
                            ) {
                                Text(
                                    text = "Experience",
                                    fontSize = dynamicFontSize * 1.2f,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = review.experience.toString(),
                                fontSize = dynamicFontSize * 1.5f,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                ElevatedCard(
                    modifier = Modifier
                        .padding(
                            10.dp, // left
                            10.dp,
                            10.dp, // right
                            0.dp
                        )
                        .fillMaxWidth()
                        .heightIn(60.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(0.dp)
                    ) {
                        Row {
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
                                text = "%.1f".format(review.finalScore),
                                fontSize = dynamicFontSize * 2f,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End, // TODO: Work on this text align
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 0.dp, 5.dp, 0.dp)
                            )
                        }
                    }
                }

                // TODO: Eventually, I will have the user be able to have a written review section
                //  which will then be displayed here in a text box

            } ?: run {Text(
                text = "Failed to get information. Failed to get info for ID: $id"
            )}
        }
    }
}