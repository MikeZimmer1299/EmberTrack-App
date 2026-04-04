package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.WhiskeyReviewDao
import com.example.cigarsandwhiskey.generalFunctions.DropdownMenu
import com.example.cigarsandwhiskey.generalFunctions.InputTextField
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


// TODO: New secondary screen for when the user wishes to create a new
//  review for a recently enjoyed whiskey
@Composable
fun NewWhiskeyReview(
    navController: NavController,
    whiskeyReviewDao: WhiskeyReviewDao,
    scope: CoroutineScope){

    // TIPS: Whiskey params for review
    var whiskeyBrand by remember { mutableStateOf("") }
    var whiskeyName by remember { mutableStateOf("") }

    var openAlertDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ) {

        // TIPS: Card for Distillery and Whiskey Name
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    45.dp,
                    10.dp, // right
                    0.dp
                )
                .fillMaxWidth()
                .heightIn(130.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Row( // TIPS: Row for stating Whiskey Brand
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Distillery:",
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
                Spacer(modifier = Modifier.width(65.dp))

                // TODO: Whiskey Brand (Dropdown menu)
                var whiskeyBrandsList = whiskeyBrandsList()
                DropdownMenu(
                    whiskeyBrandsList,
                    "Choose a Brand",
                    onItemClick = {whiskeyBrand = whiskeyBrandsList[it]}
                )
            }

            Row( // TIPS: Row for whiskey name
                modifier = Modifier.padding(0.dp, 12.dp)
            ){
                Text(
                    text = "Name:",
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
                Spacer(modifier = Modifier.width(116.dp))

                InputTextField(
                    whiskeyName,
                    onTextChange = {whiskeyName = it},
                    placeholder = "Enter Whiskey Name"
                )
            }
        }

        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////

        var whiskeyType by remember { mutableStateOf("") }
        var whiskeyOrigin by remember { mutableStateOf("") }
        var whiskeyAge by remember { mutableStateOf("") }
        var whiskeyProof by remember { mutableStateOf("") }

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
        ){
            Row( // TIPS: Row for whiskey name
                modifier = Modifier.padding(0.dp, 12.dp)
            ){
                Text(
                    text = "Type:",
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
                Spacer(modifier = Modifier.width(132.dp))

                var whiskeyTypeList = whiskeyTypesList()
                DropdownMenu(
                    whiskeyTypeList,
                    "Choose a Type",
                    onItemClick = {whiskeyType = whiskeyTypeList[it]}
                )
            }

            Row( // TIPS: Dropdown Menu for Origin
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Origin:",
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
                Spacer(modifier = Modifier.width(113.dp))

                var whiskeyOriginList = whiskeyOriginList()
                DropdownMenu(
                    whiskeyOriginList,
                    "Choose an Origin",
                    onItemClick = {whiskeyType = whiskeyOriginList[it]}
                )
            }

            Row( // TIPS: Dropdown Menu for Age Statement
                modifier = Modifier.padding(0.dp, 12.dp)
            ){
                Text(
                    text = "Aging:",
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
                Spacer(modifier = Modifier.width(115.dp))

                var whiskeyAgeList = whiskeyAgeList()
                DropdownMenu(
                    whiskeyAgeList,
                    "Age Statement",
                    onItemClick = {whiskeyType = whiskeyAgeList[it]}
                )
            }

            Row( // TIPS: Text box for Proof
                modifier = Modifier.padding(0.dp, 12.dp)
            ){
                Text(
                    text = "Proof:",
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
                Spacer(modifier = Modifier.width(120.dp))

                InputTextField(
                    whiskeyProof,
                    onTextChange = {whiskeyProof = it},
                    placeholder = "Enter the Proof",
                    KeyboardType.Number
                )
            }
        }

        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////

        var whiskeyFlavors by remember { mutableStateOf("") }
        var whiskeyAroma by remember { mutableStateOf("") }
        var mouthFeel by remember { mutableStateOf("") }

        // TODO: Card for Flavors



        // TODO: Card for Aromas



        // TODO: Card for Mouthfeel



        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////

        var score by remember { mutableStateOf("") }

        // TODO: Card for Overall Score



        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////

        // TIPS: Card for finishing the review and adding it to whiskey reviews list
        var newReview by remember {mutableStateOf(WhiskeyReviews())}

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
        ){
            Row{
                Text(
                    text = "Finish Review:",
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp, 5.dp, 0.dp, 8.dp)
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
                            overallScore = score
                        )
                        if(!whiskeyReviewCompletion(newReview)){
                            openAlertDialog = true
                            Log.d("Review", "Review is NOT complete")
                        } else {
                            scope.launch {
                                try {
                                    whiskeyReviewDao.insertReview(newReview)
                                    Log.d("Review", "Review is added")

                                    // returns to previous screen after successful save
                                    navController.popBackStack()
                                } catch (e: Exception){
                                    Log.d("Database Error", "Unable to save review")
                                }

                            }
                        }
                    },
                    modifier = Modifier
                        .padding(10.dp, 10.dp, 0.dp, 0.dp)
                        .height(45.dp)
//                        .width(115.dp)
                    ,
                    colors = ButtonDefaults.buttonColors(lushForestGreenDark)
                ) {
                    Text(text = "Add Review")
                }

                /*
                 * If not filled, have popup or some notification for user to
                 * finish the review before they are able to add the review
                 */
                if(openAlertDialog){
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