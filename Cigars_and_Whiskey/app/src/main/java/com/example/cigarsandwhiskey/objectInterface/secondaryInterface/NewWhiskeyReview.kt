package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.WhiskeyReviewDao
import com.example.cigarsandwhiskey.generalFunctions.DropdownMenu
import com.example.cigarsandwhiskey.generalFunctions.InputTextField
import com.example.cigarsandwhiskey.generalFunctions.ratingBar
import com.example.cigarsandwhiskey.generalFunctions.whiskeyAgeList
import com.example.cigarsandwhiskey.generalFunctions.whiskeyBrandsList
import com.example.cigarsandwhiskey.generalFunctions.whiskeyOriginList
import com.example.cigarsandwhiskey.generalFunctions.whiskeyTypesList
import com.example.cigarsandwhiskey.objects.WhiskeyReviews
import com.example.cigarsandwhiskey.specializedFunctions.ReviewWarning
import com.example.cigarsandwhiskey.specializedFunctions.whiskeyReviewCompletion
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight

import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NewWhiskeyReview(
    navController: NavController,
    whiskeyReviewDao: WhiskeyReviewDao,
    scope: CoroutineScope) {

    // TIPS: Whiskey params for review
    var whiskeyBrand by remember { mutableStateOf("") }
    var whiskeyName by remember { mutableStateOf("") }
    var whiskeyType by remember { mutableStateOf("") }
    var whiskeyOrigin by remember { mutableStateOf("") }
    var whiskeyAge by remember { mutableStateOf("") }
    var whiskeyProof by remember { mutableStateOf("") }
    var whiskeyFlavors by remember { mutableStateOf("") }
    var whiskeyAroma by remember { mutableStateOf("") }
    var mouthFeel by remember { mutableStateOf("") }
    var score by remember { mutableFloatStateOf(0f) }

    var openAlertDialog by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.08f).sp

    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
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
                    text = "New Whiskey Review",
                    fontSize = dynamicFontSize * 1.1f,
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

            // TIPS: Card for Distillery and Whiskey Name
            ElevatedCard(
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        15.dp,
                        10.dp, // right
                        0.dp
                    )
                    .fillMaxWidth()
                    .heightIn(110.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating Whiskey Brand
                        modifier = Modifier.padding(0.dp, 3.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(0.44f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Distillery:",
                                fontSize = dynamicFontSize,
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
                        }

                        val menuWeight = screenWidth * .0011f
                        val whiskeyBrandsList = whiskeyBrandsList()
                        DropdownMenu(
                            whiskeyBrandsList,
                            whiskeyBrand.ifEmpty { "Choose a Brand" },
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            onItemClick = { whiskeyBrand = whiskeyBrandsList[it] }
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for whiskey name
                        modifier = Modifier.padding(0.dp, 3.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(0.44f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Name:",
                                fontSize = dynamicFontSize,
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
                        }

                        val menuWeight = screenWidth * .0011f
                        InputTextField(
                            whiskeyName,
                            onTextChange = { whiskeyName = it },
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            placeholder = "Enter Whiskey Name"
                        )
                    }
                }
            }

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            // TIPS: Card for Type, Proof, and Age Statement
            ElevatedCard(
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        15.dp,
                        10.dp, // right
                        0.dp
                    )
                    .fillMaxWidth()
                    .heightIn(135.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for whiskey name
                        modifier = Modifier.padding(0.dp, 3.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(0.44f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Type:",
                                fontSize = dynamicFontSize,
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
                        }

                        val menuWeight = screenWidth * .0011f
                        val whiskeyTypeList = whiskeyTypesList()
                        DropdownMenu(
                            whiskeyTypeList,
                            whiskeyType.ifEmpty { "Choose a Type" },
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            onItemClick = { whiskeyType = whiskeyTypeList[it] }
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Dropdown Menu for Origin
                        modifier = Modifier.padding(0.dp, 3.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(0.44f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Origin:",
                                fontSize = dynamicFontSize,
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
                        }

                        val menuWeight = screenWidth * .0011f
                        val whiskeyOriginList = whiskeyOriginList()
                        DropdownMenu(
                            whiskeyOriginList,
                            whiskeyOrigin.ifEmpty { "Choose an Origin" },
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            onItemClick = { whiskeyOrigin = whiskeyOriginList[it] }
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Dropdown Menu for Age Statement
                        modifier = Modifier.padding(0.dp, 3.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(0.44f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Aging:",
                                fontSize = dynamicFontSize,
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
                        }

                        val menuWeight = screenWidth * .0011f
                        val whiskeyAgeList = whiskeyAgeList()
                        DropdownMenu(
                            whiskeyAgeList,
                            whiskeyAge.ifEmpty { "Age Statement" },
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            onItemClick = { whiskeyAge = whiskeyAgeList[it] }
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Text box for Proof
                        modifier = Modifier.padding(0.dp, 3.dp)
                    ) {
                        Box(
                            modifier = Modifier.weight(0.44f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Proof:",
                                fontSize = dynamicFontSize,
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
                        }

                        val menuWeight = screenWidth * .0011f
                        InputTextField(
                            whiskeyProof,
                            onTextChange = { whiskeyProof = it },
                            placeholder = "Enter the Proof",
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            KeyboardType.Number
                        )
                    }
                }
            }

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            // TIPS: Card for Flavors
            ElevatedCard(
                modifier = Modifier
                    .padding(10.dp, 10.dp, 10.dp, 0.dp)
                    .fillMaxWidth()
                    .heightIn(130.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row {
                        Text(
                            text = "Flavors",
                            fontSize = dynamicFontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(6.dp, 5.dp, 0.dp, 0.dp)
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
                            value = whiskeyFlavors,
                            onValueChange = { whiskeyFlavors = it },
                            placeholder = {
                                Text(
                                    "What are the flavors?",
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
//                                .fillMaxHeight()
                                .fillMaxWidth()
                                .height((screenWidth * .3f).dp)
                        )
                    }
                }
            }


            // TIPS: Card for Aromas
            ElevatedCard(
                modifier = Modifier
                    .padding(10.dp, 10.dp, 10.dp, 0.dp)
                    .fillMaxWidth()
                    .heightIn(130.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row {
                        Text(
                            text = "Aromas",
                            fontSize = dynamicFontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(6.dp, 5.dp, 0.dp, 0.dp)
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
                            value = whiskeyAroma,
                            onValueChange = { whiskeyAroma = it },
                            placeholder = {
                                Text(
                                    "What are the aromas?",
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
//                                .fillMaxHeight()
                                .fillMaxWidth()
                                .height((screenWidth * .3f).dp)
                        )
                    }
                }
            }


            // TIPS: Card for Mouthfeel
            ElevatedCard(
                modifier = Modifier
                    .padding(10.dp, 10.dp, 10.dp, 0.dp)
                    .fillMaxWidth()
                    .heightIn(130.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row {
                        Text(
                            text = "Mouthfeel",
                            fontSize = dynamicFontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(6.dp, 5.dp, 0.dp, 0.dp)
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
                            value = mouthFeel,
                            onValueChange = { mouthFeel = it },
                            placeholder = {
                                Text(
                                    "What is the mouthfeel?",
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
//                                .fillMaxHeight()
                                .fillMaxWidth()
                                .height((screenWidth * .3f).dp)
                        )
                    }
                }
            }


            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            // TIPS: Card for Overall Score
            ElevatedCard(
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        10.dp,
                        10.dp, // right
                        0.dp
                    )
                    .fillMaxWidth()
                    .heightIn(140.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Row {
                    Text(
                        text = "Overall Score",
                        fontSize = dynamicFontSize * 1.2f,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(6.dp, 5.dp, 0.dp, 3.dp)
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

                // TIPS: Initiates the rating bar click-ability
                ratingBar(
                    rating = score,
                    onRatingChanged = { score = it }
                )
            }


            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            // TIPS: Card for finishing the review and adding it to whiskey reviews list
            var newReview by remember { mutableStateOf(WhiskeyReviews()) }

            ElevatedCard(
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        10.dp,
                        10.dp, // right
                        60.dp
                    )
                    .fillMaxWidth()
                    .heightIn(60.dp),
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
                            fontSize = dynamicFontSize * 1.1f,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(4.dp, 5.dp, 0.dp, 8.dp)
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

                        Spacer(modifier = Modifier.width((screenWidth * .04f).dp))
                        // TODO: Test the weight method for this, instead of using screen width

                        Button(
                            onClick = {
                                // TODO: Add `newReview = newReview.copy()`
                                newReview = newReview.copy(
                                    brand = whiskeyBrand,
                                    whiskeyName = whiskeyName,
                                    type = whiskeyType,
                                    origin = whiskeyOrigin,
                                    proof = whiskeyProof,
                                    ageStatement = whiskeyAge,
                                    flavors = whiskeyFlavors,
                                    aroma = whiskeyAroma,
                                    mouthFeel = mouthFeel,
                                    overallScore = score.toInt()
                                )
                                if (!whiskeyReviewCompletion(newReview)) {
                                    openAlertDialog = true
                                    Log.d("Review", "Review is NOT complete")
                                } else {
                                    scope.launch {
                                        try {
                                            whiskeyReviewDao.insertReview(newReview)
                                            Log.d("Review", "Review is added")

                                            // returns to previous screen after successful save
                                            navController.popBackStack()
                                        } catch (e: Exception) {
                                            Log.d("Database Error", "Unable to save review")
                                        }

                                    }
                                }
                            },
                            modifier = Modifier
                                .padding(0.dp, 5.dp, 0.dp, 5.dp)
                                .height(45.dp),
                            colors = ButtonDefaults.buttonColors(lushForestGreenDark)
                        ) {
                            Text(text = "Add Review", fontSize = dynamicFontSize * .4f)
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