package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
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
){

    var cigarBrand by remember { mutableStateOf("") }
    var cigarName by remember { mutableStateOf("") }
    var cigarOrigin by remember { mutableStateOf("") }
    var cigarLength by remember { mutableStateOf("") }
    var cigarRingGauge by remember { mutableStateOf("") }
    var cigarQuantity by remember { mutableStateOf("") }

    var openAlertDialog by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

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
            Text(text = "Add Cigar to Collection", fontSize = 40.sp, fontWeight = FontWeight.Bold,
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

        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    15.dp,
                    10.dp, // right
                    5.dp
                )
                .fillMaxWidth()
                .height(200.dp),
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

                var cigarBrandList = cigarBrandsList()
                DropdownMenu(
                    cigarBrandList,
//                    selectedIndex, // this may be unnecessary
                    "Choose a Brand",
                    modifier = Modifier.weight(1f).padding(end = 12.dp),
                    onItemClick = { cigarBrand = cigarBrandList[it] }
                )
//                Log.d("Output", "Viewing the brand: $chosenBrand")
            }

            Row( // TIPS: Row for stating CIGAR NAME
                modifier = Modifier
                    .padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Cigar Name:",
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

                // TIPS: Text Box to enter cigar name
                InputTextField(
                    cigarName,
                    onTextChange = { cigarName = it },
                    modifier = Modifier.weight(1f).padding(end = 12.dp),
                    placeholder = "Enter Cigar Name"
                )
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
                Spacer(modifier = Modifier.width(17.dp))

                // TIPS: Dropdown menu of countries
                var cigarOriginList = cigarOriginList()
                DropdownMenu(
                    cigarOriginList,
//                    selectedIndex, // this may be unnecessary
                    "Country of Origin",
                    modifier = Modifier.weight(1f).padding(end = 12.dp),
                    onItemClick = { cigarOrigin = cigarOriginList[it] }
                )
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
            Row( // Row for stating CIGAR LENGTH
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Cigar Length:",
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
                Spacer(modifier = Modifier.width(8.dp))

                // TIPS: Text box for cigar length
                InputTextField(
                    cigarLength,
                    onTextChange = { cigarLength = it },
                    placeholder = "Enter Cigar Length",
                    modifier = Modifier.weight(1f).padding(end = 12.dp),
                    KeyboardType.Number
                )
            }


            Row( // TIPS: Row for stating CIGAR RING GAUGE
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Ring Gauge:",
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

                Spacer(modifier = Modifier.width(30.dp))

                // TIPS: Text box for cigar length
                InputTextField(
                    cigarRingGauge,
                    onTextChange = { cigarRingGauge = it },
                    placeholder = "Enter Ring Gauge",
                    modifier = Modifier.weight(1f).padding(end = 12.dp),
                    KeyboardType.Number
                )
            }

            Row(
                modifier = Modifier.padding(0.dp, 12.dp)
            ) {
                Text(
                    text = "Quantity:",
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
                Spacer(modifier = Modifier.width(79.dp))

                InputTextField(
                    cigarQuantity,
                    onTextChange = { cigarQuantity = it },
                    placeholder = "Enter # of Cigars",
                    modifier = Modifier.weight(1f).padding(end = 12.dp),
                    KeyboardType.Number
                )
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
                .height(65.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Row {
                Text(
                    text = "Add to Humidor:",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp, 7.dp, 0.dp, 0.dp)
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
                            Log.d("Review", "Review is NOT complete")
                            // TIPS: unable to call Composable from within here, made
                            //  a mutableStateOf boolean. cigarReviewCompletion called below
                        } else {
                            scope.launch {
                                try {
                                    myCigarsDao.insertCigar(newToCollection)
                                    Log.d("CigarCollection", "Cigars added to collection")

                                    navController.popBackStack()
                                } catch (e: Exception){
                                    Log.d("Database Error", "Unable to save to collection")
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(10.dp, 10.dp, 0.dp, 0.dp)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(lushForestGreenDark)
                ) {
                    Text(text = "Add Cigars")
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