package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.MyWhiskeyDao
import com.example.cigarsandwhiskey.generalFunctions.DropdownMenu
import com.example.cigarsandwhiskey.generalFunctions.InputTextField
import com.example.cigarsandwhiskey.generalFunctions.whiskeyAgeList
import com.example.cigarsandwhiskey.generalFunctions.whiskeyBrandsList
import com.example.cigarsandwhiskey.generalFunctions.whiskeyOriginList
import com.example.cigarsandwhiskey.generalFunctions.whiskeyTypesList
import com.example.cigarsandwhiskey.objects.MyWhiskey
import com.example.cigarsandwhiskey.specializedFunctions.ReviewWarning
import com.example.cigarsandwhiskey.specializedFunctions.addNewWhiskeyCompletion
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AddWhiskey(
    navController: NavController,
    myWhiskeyDao: MyWhiskeyDao,
    scope: CoroutineScope
){
    var whiskeyBrand by remember{ mutableStateOf("") }
    var whiskeyName by remember { mutableStateOf("") }
    var whiskeyOrigin by remember { mutableStateOf("") }
    var whiskeyType by remember { mutableStateOf("") }
    var whiskeyProof by remember { mutableStateOf("") }
    var whiskeyAge by remember { mutableStateOf("") }
    var bottleSize by remember { mutableStateOf("") }

    var openAlertDialog by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState())
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ){
        // TIPS: Card to display screen title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 30.dp, 0.dp, 0.dp),
        ) {
            Text(text = "Add Whiskey to Collect.", fontSize = dynamicFontSize * 1.2f, fontWeight = FontWeight.Bold,
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

        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////

        // TIPS: Card for Distillery and Whiskey Name
        ElevatedCard(
            modifier = Modifier
                .padding(10.dp,
                    15.dp,
                    10.dp,
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
                Row(
                    modifier = Modifier.padding(0.dp, 6.dp)
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
                    Spacer(modifier = Modifier.width(65.dp))

                    val menuWeight = screenWidth * .0011f
                    val whiskeyBrandList = whiskeyBrandsList()
                    DropdownMenu(
                        whiskeyBrandList,
                        initialText = "Choose a Brand",
                        modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                        onItemClick = { whiskeyBrand = whiskeyBrandList[it]}
                    )
                }
            }


            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Row(
                    modifier = Modifier.padding(0.dp, 6.dp)
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
                    Spacer(modifier = Modifier.width(116.dp))

                    val menuWeight = screenWidth * .0011f
                    InputTextField(
                        whiskeyName,
                        onTextChange = {whiskeyName = it},
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
                .heightIn(155.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Row( // TIPS: Row for whiskey name
                    modifier = Modifier.padding(0.dp, 6.dp)
                ){
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
                    Spacer(modifier = Modifier.width(132.dp))

                    val menuWeight = screenWidth * .0011f
                    val whiskeyTypeList = whiskeyTypesList()
                    DropdownMenu(
                        whiskeyTypeList,
                        "Choose a Type",
                        modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                        onItemClick = {whiskeyType = whiskeyTypeList[it]}
                    )
                }
            }

            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Row( // TIPS: Dropdown Menu for Origin
                    modifier = Modifier.padding(0.dp, 6.dp)
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
                    Spacer(modifier = Modifier.width(113.dp))

                    val menuWeight = screenWidth * .0011f
                    val whiskeyOriginList = whiskeyOriginList()
                    DropdownMenu(
                        whiskeyOriginList,
                        "Choose an Origin",
                        modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                        onItemClick = {whiskeyOrigin = whiskeyOriginList[it]}
                    )
                }
            }

            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Row( // TIPS: Dropdown Menu for Age Statement
                    modifier = Modifier.padding(0.dp, 6.dp)
                ){
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
                    Spacer(modifier = Modifier.width(115.dp))

                    val menuWeight = screenWidth * .0011f
                    val whiskeyAgeList = whiskeyAgeList()
                    DropdownMenu(
                        whiskeyAgeList,
                        "Age Statement",
                        modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                        onItemClick = {whiskeyAge = whiskeyAgeList[it]}
                    )
                }
            }

            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Row( // TIPS: Text box for Proof
                    modifier = Modifier.padding(0.dp, 6.dp)
                ){
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
                    Spacer(modifier = Modifier.width(120.dp))

                    val menuWeight = screenWidth * .0011f
                    InputTextField(
                        whiskeyProof,
                        onTextChange = {whiskeyProof = it},
                        placeholder = "Enter the Proof",
                        modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                        KeyboardType.Number
                    )
                }
            }
        }

        ///////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////

        // TIPS: Card for bottle size and adding
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
                Row( // TIPS: Text field for bottle size
                    modifier = Modifier.padding(0.dp, 6.dp)
                ) {
                    Text(
                        text = "Size:",
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

                    Spacer(modifier = Modifier.width(140.dp))

                    val menuWeight = screenWidth * .0011f
                    InputTextField(
                        bottleSize,
                        onTextChange = {bottleSize = it},
                        placeholder = "Enter Bottle Size",
                        modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                        KeyboardType.Number
                    )
                }
            }

            var newToCollection by remember { mutableStateOf(MyWhiskey()) }

            Column(
                modifier = Modifier.padding(5.dp)
            ) {
                Row( // TIPS: Button to add whiskey to collection
                    modifier = Modifier.padding(0.dp, 6.dp)
                ){
                    Text(
                        text = "Add Whiskey:",
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

                    Spacer(modifier = Modifier.width(80.dp))

                    Button(
                        onClick = {
                            newToCollection = newToCollection.copy(
                                brand = whiskeyBrand,
                                name = whiskeyName,
                                proof = whiskeyProof,
                                type = whiskeyType,
                                origin = whiskeyOrigin,
                                ageStatement = whiskeyAge,
                                bottleSize = bottleSize
                            )
                            if(!addNewWhiskeyCompletion(newToCollection)){
                                openAlertDialog = true
                                Log.d("Review", "Whiskey is NOT added")
                            } else {
                                scope.launch {
                                    try {
                                        myWhiskeyDao.insertWhiskey(newToCollection)
                                        Log.d("WhiskeyCollection", "Whiskey added to collection")

                                        navController.popBackStack()
                                    } catch (e: Exception){
                                        Log.d("Database Error", "Unable to save to collection")
                                    }
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(lushForestGreenDark)
                    ) {
                        Text(text = "Add Whiskey")
                    }
                }
            }
            if(openAlertDialog){
                ReviewWarning(
                    onDismissRequest = { openAlertDialog = false },
                    onConfirmation = {
                        openAlertDialog = false
                        println("Confirmation Registered")
                    },
                    dialogTitle = "Warning!",
                    dialogText = "You have not finished adding the necessary information!"
                )
            }
        }
    }

}