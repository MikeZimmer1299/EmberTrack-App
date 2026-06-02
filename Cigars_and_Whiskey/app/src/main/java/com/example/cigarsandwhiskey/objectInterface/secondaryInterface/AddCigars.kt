package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.LaunchedEffect
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
import com.example.cigarsandwhiskey.dataAccessObjects.MyCigarsDao
import com.example.cigarsandwhiskey.dataAccessObjects.MyHumidorDao
import com.example.cigarsandwhiskey.generalFunctions.DropdownMenu
import com.example.cigarsandwhiskey.generalFunctions.InputTextField
import com.example.cigarsandwhiskey.generalFunctions.cigarBrandsList
import com.example.cigarsandwhiskey.generalFunctions.cigarOriginList
import com.example.cigarsandwhiskey.objects.HumidorWithCigars
import com.example.cigarsandwhiskey.objects.MyCigars
import com.example.cigarsandwhiskey.specializedFunctions.ReviewWarning
import com.example.cigarsandwhiskey.specializedFunctions.addNewCigarCompletion
import com.example.cigarsandwhiskey.specializedFunctions.cigarReviewCompletion
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun AddCigars(
    navController: NavController,
    myCigarsDao: MyCigarsDao,
    scope: CoroutineScope
) {

    var cigarBrand by remember { mutableStateOf("") }
    var cigarName by remember { mutableStateOf("") }
    var cigarOrigin by remember { mutableStateOf("") }
    var cigarLength by remember { mutableStateOf("") }
    var cigarRingGauge by remember { mutableStateOf("") }
    var cigarQuantity by remember { mutableStateOf("") }

    var openAlertDialog by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    Box(modifier = Modifier.fillMaxSize()) {
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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 30.dp, 0.dp, 0.dp),
            ) {
                Text(
                    text = "Add Cigar to Collection",
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

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            ElevatedCard(
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
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating CIGAR BRAND
                        modifier = Modifier.padding(0.dp, 3.dp)
                    ) {
                        Text(
                            text = "Cigar Brand:",
                            fontSize = dynamicFontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp, 3.dp, 0.dp, 0.dp)
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
//                        Spacer(modifier = Modifier.width(20.dp))
                        Spacer(modifier = Modifier.width((screenWidth * .044f).dp))

                        val menuWeight = screenWidth * .0011f
                        val cigarBrandList = cigarBrandsList()
                        DropdownMenu(
                            cigarBrandList,
//                    selectedIndex, // this may be unnecessary
                            "Choose a Brand",
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            onItemClick = { cigarBrand = cigarBrandList[it] }
                        )
//                Log.d("Output", "Viewing the brand: $chosenBrand")
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating CIGAR NAME
                        modifier = Modifier
                            .padding(0.dp, 3.dp)
                    ) {
                        Text(
                            text = "Cigar Name:",
                            fontSize = dynamicFontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp, 3.dp, 0.dp, 0.dp)
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
//                        Spacer(modifier = Modifier.width(20.dp))
                        Spacer(modifier = Modifier.width((screenWidth * .049f).dp))

                        val menuWeight = screenWidth * .0011f
                        // TIPS: Text Box to enter cigar name
                        InputTextField(
                            cigarName,
                            onTextChange = { cigarName = it },
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            placeholder = "Enter Cigar Name"
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating COUNTRY OF ORIGIN
                        modifier = Modifier.padding(0.dp, 3.dp)
                    ) {
                        Text(
                            text = "Cigar Origin:",
                            fontSize = dynamicFontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp, 3.dp, 0.dp, 0.dp)
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
//                        Spacer(modifier = Modifier.width(17.dp))
                        Spacer(modifier = Modifier.width((screenWidth * .042f).dp))

                        val menuWeight = screenWidth * .0011f
                        val cigarOriginList = cigarOriginList()
                        DropdownMenu(
                            cigarOriginList,
                            "Country of Origin",
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            onItemClick = { cigarOrigin = cigarOriginList[it] }
                        )
                    }
                }
            }

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            ElevatedCard(
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        10.dp,
                        10.dp, // right
                        5.dp
                    )
                    .fillMaxWidth()
                    .heightIn(130.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // Row for stating CIGAR LENGTH
                        modifier = Modifier.padding(0.dp, 2.dp)
                    ) {
                        Text(
                            text = "Cigar Length:",
                            fontSize = dynamicFontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp, 3.dp, 0.dp, 0.dp)
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
//                        Spacer(modifier = Modifier.width(8.dp))
                        Spacer(modifier = Modifier.width((screenWidth * .022f).dp))

                        val menuWeight = screenWidth * .0011f
                        InputTextField(
                            cigarLength,
                            onTextChange = { cigarLength = it },
                            placeholder = "Enter Cigar Length",
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            KeyboardType.Number
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row( // TIPS: Row for stating CIGAR RING GAUGE
                        modifier = Modifier.padding(0.dp, 2.dp)
                    ) {
                        Text(
                            text = "Ring Gauge:",
                            fontSize = dynamicFontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp, 3.dp, 0.dp, 0.dp)
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

//                        Spacer(modifier = Modifier.width(30.dp))
                        Spacer(modifier = Modifier.width((screenWidth * .066f).dp))

                        val menuWeight = screenWidth * .0011f
                        InputTextField(
                            cigarRingGauge,
                            onTextChange = { cigarRingGauge = it },
                            placeholder = "Enter Ring Gauge",
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            KeyboardType.Number
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(0.dp, 2.dp)
                    ) {
                        Text(
                            text = "Quantity:",
                            fontSize = dynamicFontSize,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp, 3.dp, 0.dp, 0.dp)
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
//                        Spacer(modifier = Modifier.width(79.dp))
                        Spacer(modifier = Modifier.width((screenWidth * .168f).dp))

                        val menuWeight = screenWidth * .0011f
                        InputTextField(
                            cigarQuantity,
                            onTextChange = { cigarQuantity = it },
                            placeholder = "Enter # of Cigars",
                            modifier = Modifier.weight(menuWeight).padding(end = 4.dp),
                            KeyboardType.Number
                        )
                    }
                }
            }

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            var newToCollection by remember { mutableStateOf(MyCigars()) }

            ElevatedCard( // TIPS: Add cigars to collection
                modifier = Modifier
                    .padding(
                        10.dp, // left
                        10.dp,
                        10.dp, // right
                        60.dp
                    )
                    .fillMaxWidth()
                    .heightIn(3.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Row {
                        Text(
                            text = "Add Collection:",
                            fontSize = dynamicFontSize * 1.1f,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(5.dp, 5.dp, 15.dp, 5.dp)
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

                        Spacer(modifier = Modifier.width((screenWidth * .03f).dp))

                        Button(
                            onClick = {
                                newToCollection = newToCollection.copy(
                                    cigarBrand = cigarBrand,
                                    cigarName = cigarName,
                                    countryOfOrigin = cigarOrigin,
                                    sizeLength = cigarLength,
                                    ringGauge = cigarRingGauge,
                                    quantity = cigarQuantity
                                )
                                if (!addNewCigarCompletion(newToCollection)) {
                                    openAlertDialog = true
                                    Log.d("Review", "Cigar is NOT added")
                                    // TIPS: unable to call Composable from within here, made
                                    //  a mutableStateOf boolean. cigarReviewCompletion called below
                                } else {
                                    scope.launch {
                                        try {
                                            myCigarsDao.insertCigar(newToCollection)
                                            Log.d("CigarCollection", "Cigars added to collection")

                                            navController.popBackStack()
                                        } catch (e: Exception) {
                                            Log.d("Database Error", "Unable to save to collection")
                                        }
                                    }
                                }
                            },
                            modifier = Modifier
                                .padding(0.dp, 5.dp, 0.dp, 5.dp)
                                .height(40.dp),
                            colors = ButtonDefaults.buttonColors(lushForestGreenDark)
                        ) {
                            Text(text = "Add Cigars")
                        }
                    }
                }
                if (openAlertDialog) {
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
}