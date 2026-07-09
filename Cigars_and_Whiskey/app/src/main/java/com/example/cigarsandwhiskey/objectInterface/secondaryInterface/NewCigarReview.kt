package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.CigarReviewDao
import com.example.cigarsandwhiskey.generalFunctions.DropdownMenu
import com.example.cigarsandwhiskey.generalFunctions.InputTextField
import com.example.cigarsandwhiskey.generalFunctions.cigarBrandsList
import com.example.cigarsandwhiskey.generalFunctions.cigarOriginList
import com.example.cigarsandwhiskey.generalFunctions.ratingBar
import com.example.cigarsandwhiskey.objects.CigarReviews
import com.example.cigarsandwhiskey.specializedFunctions.ReviewWarning
import com.example.cigarsandwhiskey.specializedFunctions.cigarReviewCompletion
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Secondary screen for when the user wishes to create a new
//  review for a recently enjoyed cigar
@Composable
fun NewCigarReview(
    navController: NavController,
    cigarDao: CigarReviewDao,
    scope: CoroutineScope
) {

    // TIPS: Cigar Brand, Name, Origin, Length, and Ring Gauge objects
    var cigarBrand by remember { mutableStateOf("") }
    var cigarName by remember { mutableStateOf("") }
    var cigarCountry by remember { mutableStateOf("") }
    var cigarLength by remember { mutableStateOf("") }
    var cigarRingGauge by remember { mutableStateOf("") }
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

    // TODO: Newly added: Notes
    var notes by remember { mutableStateOf("") }

    var openAlertDialog by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.07f).sp
    val dynamicTestSize = (400 * 0.071f).sp

    Column(
        modifier = Modifier.fillMaxSize().background(lushForestGreenDark),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
        ) {
            // TIPS: Card to display screen title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 30.dp, 0.dp, 0.dp),
            ) {
                Text(
                    text = "New Cigar Review",
                    fontSize = dynamicFontSize * 1.2f,
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

            // TIPS: This card holds the Cigar Brand, Name, and Country of Origin
            ElevatedCard(
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        15.dp,
                        10.dp, // right
                        0.dp
                    )
                    .fillMaxWidth()
                    .heightIn(150.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating CIGAR BRAND
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(0.5f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Cigar Brand:",
                                fontSize = dynamicFontSize * 1.1f,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                modifier = Modifier
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
                        }

                        val menuWeight = screenWidth * .0011f
                        val cigarBrandList = cigarBrandsList()
                        DropdownMenu( // TIPS: Dropdown menu of cigar brands
                            cigarBrandList,
                            cigarBrand.ifEmpty { "Choose a Brand" },
                            modifier = Modifier.weight(menuWeight).padding(end = 7.dp),
                            onItemClick = { cigarBrand = cigarBrandList[it] }
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating CIGAR NAME
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(0.5f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Cigar Name:",
                                fontSize = dynamicFontSize * 1.1f,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                modifier = Modifier
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
                        }

                        val menuWeight = screenWidth * .0011f

                        InputTextField( // TIPS: Text Box to enter cigar name
                            cigarName,
                            onTextChange = { cigarName = it },
                            modifier = Modifier.weight(menuWeight).padding(end = 7.dp),
                            placeholder = "Enter Cigar Name"
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating COUNTRY OF ORIGIN
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(0.5f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Cigar Origin:",
                                fontSize = dynamicFontSize * 1.1f,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                modifier = Modifier
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
                        }

                        val menuWeight = screenWidth * .0011f
                        val cigarOriginList = cigarOriginList()
                        DropdownMenu( // TIPS: Dropdown menu of countries
                            cigarOriginList,
                            cigarCountry.ifEmpty { "Country of Origin" },
                            modifier = Modifier.weight(menuWeight).padding(end = 7.dp),
                            onItemClick = { cigarCountry = cigarOriginList[it] }
                        )
                    }
                }
            }

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            // TIPS: Card for length and ring gauge
            ElevatedCard(
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        15.dp,
                        10.dp, // right
                        0.dp
                    )
                    .fillMaxWidth()
                    .heightIn(105.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating CIGAR LENGTH
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(0.5f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Cigar Length:",
                                fontSize = dynamicFontSize * 1.05f,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                modifier = Modifier
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
                        }

                        val menuWeight = screenWidth * .0011f
                        // TIPS: Text box for cigar length
                        InputTextField(
                            cigarLength,
                            onTextChange = { cigarLength = it },
                            placeholder = "Enter Cigar Length",
                            modifier = Modifier.weight(menuWeight).padding(end = 7.dp),
                            KeyboardType.Number
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating CIGAR RING GAUGE
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(0.5f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Ring Gauge:",
                                fontSize = dynamicFontSize * 1.1f,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                modifier = Modifier
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
                        }

                        val menuWeight = screenWidth * .0011f
                        // TIPS: Text box for cigar length
                        InputTextField(
                            cigarRingGauge,
                            onTextChange = { cigarRingGauge = it },
                            placeholder = "Enter Ring Gauge",
                            modifier = Modifier.weight(menuWeight).padding(end = 7.dp),
                            KeyboardType.Number
                        )
                    }
                }
            }


            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            // TIPS: Score Sheet Begins Here


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
                        .fillMaxWidth()
                        .heightIn(100.dp),
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

                    ratingBar(
                        rating = ratings[index],
                        onRatingChanged = { ratings[index] = it }
                    )
                }
            }

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            // TIPS: Card for personal notes
            ElevatedCard(
                modifier = Modifier
                    .padding(10.dp, 10.dp, 10.dp, 0.dp)
                    .fillMaxWidth()
                    .heightIn(130.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Row() {
                        Text(
                            text = "Notes",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp, 0.dp, 25.dp, 0.dp)
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
                    Row {
                        TextField(
                            value = notes,
                            onValueChange = { notes = it },
                            placeholder = {
                                Text(
                                    text = "Tasting notes (Optional)",
                                    modifier = Modifier.alpha(.5f)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = Color.White,
                                focusedContainerColor = Color.White,
                                unfocusedTextColor = Color.Black,
                                focusedTextColor = Color.Black,
                                unfocusedPlaceholderColor = Color.Black
                            ),
                            modifier = Modifier
                                .padding(start = 4.dp, end = 4.dp, top = 12.dp, bottom = 5.dp)
                                .fillMaxWidth()
                                .height((screenWidth * .3f).dp),
                            keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences)
                        )
                    }
                }
            }

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            // TIPS: Card for the Overall Rating (Final Score)
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
                            text = "%.1f".format(finalScore),
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

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            // TIPS: Final Card that will allow the user to create the new review, adding it
            //  to the list of Cigar Reviews

            var newReview by remember { mutableStateOf(CigarReviews()) }

            ElevatedCard(
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        10.dp,
                        10.dp, // right
                        60.dp
                    )
                    .fillMaxWidth()
                    .heightIn(40.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row {
                        Text(
                            text = "Finish Review:",
                            fontSize = dynamicFontSize * 1.25f,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp, 0.dp, 0.dp, 0.dp)
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

                        Spacer(modifier = Modifier.width((screenWidth * .05f).dp))
                        // TODO: Test the weight method for this, instead of using screen width

                        Button(
                            onClick = {
                                newReview = newReview.copy(
                                    brand = cigarBrand,
                                    cigarName = cigarName,
                                    origin = cigarCountry,
                                    sizeLength = cigarLength,
                                    ringGauge = cigarRingGauge,
                                    draw = ratings[0].toInt(),
                                    burn = ratings[1].toInt(),
                                    construction = ratings[2].toInt(),
                                    flavors = ratings[3].toInt(),
                                    aroma = ratings[4].toInt(),
                                    smokeProduction = ratings[5].toInt(),
                                    experience = ratings[6].toInt(),
                                    notes = notes,
                                    finalScore = finalScore,
                                )
                                if (!cigarReviewCompletion(newReview)) {
                                    openAlertDialog = true
                                    Log.d("Review", "Review is NOT complete")
                                    // TIPS: unable to call Composable from within here, made
                                    //  a mutableStateOf boolean. cigarReviewCompletion called below
                                } else {
                                    scope.launch {
                                        try {
                                            // saves review to local storage/database
                                            cigarDao.insertReview((newReview))
                                            Log.d("Review", "Final Score: ${newReview.finalScore}")

                                            // returns to previous screen after successful save
                                            navController.popBackStack()
                                        } catch (e: Exception) {
                                            Log.d("Database Error", "Unable to save review")
                                        }
                                    }
                                }

//                        Log.d("Review", "Review Final Score: ${newReview.finalScore}")
                            },
                            modifier = Modifier
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                                .heightIn(40.dp),
                            colors = ButtonDefaults.buttonColors(lushForestGreenDark)
                        ) {
                            Text(text = "Add Review", fontSize = dynamicFontSize * .40f)
                        }
                    }
                }
                /*
             * If not filled, have popup or some notification for user to
             * finish the review before they are able to add the review
             */
                if (openAlertDialog) {
                    ReviewWarning(
                        onDismissRequest = { openAlertDialog = false },
                        onConfirmation = {
                            openAlertDialog = false
                            println("Confirmation Registered")
                        },
                        dialogTitle = "Warning!",
                        dialogText = "You have not finished your review!"
                    )
                }
            }
        }
    }
}