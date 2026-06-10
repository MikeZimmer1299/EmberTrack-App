package com.example.cigarsandwhiskey.objectInterface

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.MyCigarsDao
import com.example.cigarsandwhiskey.objects.MyCigars
import com.example.cigarsandwhiskey.specializedFunctions.DeleteCigarOption
import com.example.cigarsandwhiskey.specializedFunctions.DeleteWhiskeyOption
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyCigarsScreen(
    navController: NavController,
    myCigarsDao: MyCigarsDao,
    scope: CoroutineScope
) {

    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    var deleteCigar by remember { mutableStateOf(false) }
    var cigarToDelete by remember { mutableStateOf<MyCigars?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp)
                .verticalScroll(rememberScrollState()),
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
                    text = "My Cigars",
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

            // TODO: First starting off as if the user only has one humidor, will implement
            //  more than one humidor once the single humidor object is in working order

            val myCigarsList by myCigarsDao.getAllCigars()
                .collectAsStateWithLifecycle(emptyList())

            Log.d("Output", "myCigarsDao.getAllCigars() success")

            myCigarsList.forEach { cigars ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(
                            10.dp, // left
                            10.dp,
                            10.dp, // right
                            5.dp
                        )
                        .fillMaxWidth()
                        .heightIn(150.dp)
                        .combinedClickable(
                            onClick = {},
                            onLongClick = {
                                deleteCigar = true
                                cigarToDelete = cigars
                            }
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp, 0.dp)
                    ) {
                        Text(
                            text = cigars.cigarBrand,
                            fontSize = dynamicFontSize * 1.2f,
                            fontWeight = FontWeight.Bold,
                            lineHeight = dynamicFontSize * 1.2f,
                            softWrap = true
                        )
                    }
                    Row(
                        modifier = Modifier.padding(10.dp, 0.dp)
                    ) {
                        Text(
                            text = cigars.cigarName,
                            fontSize = dynamicFontSize * 1.2f,
                            fontWeight = FontWeight.Bold,
                            lineHeight = dynamicFontSize * 1.2f,
                            softWrap = true
                        )
                    }
                    Row(
                        modifier = Modifier.padding(10.dp, 0.dp)
                    ) {
                        Text(
                            text = "Size: ${cigars.sizeLength} x ${cigars.ringGauge}",
                            fontSize = dynamicFontSize * 1.1f,
                            fontWeight = FontWeight.Bold,
                            lineHeight = dynamicFontSize * 1.2f,
                            softWrap = true
                        )
                    }
                    Row(
                        modifier = Modifier.padding(10.dp, 0.dp)
                    ) {
                        Text(
                            text = "Quantity: ${cigars.quantity}",
                            fontSize = dynamicFontSize * 1.1f,
                            fontWeight = FontWeight.Bold,
                            lineHeight = dynamicFontSize * 1.2f,
                            softWrap = true
                        )
                    }
                }
            }
        }

        if (deleteCigar) {
            DeleteCigarOption(
                onDismissRequest = { deleteCigar = false },
                onConfirmation = {
                    deleteCigar = false
                },
                dialogTitle = "Delete Cigar",
                dialogText = "Are you sure you want to delete this cigar from your collection?",
                cigarToDelete,
                scope,
                myCigarsDao
            )
        }

        // TODO: This button will allow a user to add a new cigar(s) to their collection
        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp, 15.dp, 10.dp, 55.dp),
//                .padding(
//                    305.dp, // left
//                    920.dp,
//                    0.dp, // right
//                    0.dp
//                ),
            containerColor = lushForestGrassLight,
            onClick = {
                navController.navigate("add_new_cigar")
            },
            icon = { Icon(Icons.Filled.Add, "Add Cigar Button") },
            text = { Text(text = "Add Cigar(s)", fontSize = dynamicFontSize * .47f) }
        )

        // TODO: Also need to think about how to add a humidor to be tracked
        //  This has been added to "later"

        ElevatedCard(
            modifier = Modifier
                .padding(20.dp)
                .height(20.dp)
        ) {
            // TIPS: Intentionally left blank. A terrible way to add spacing below the last
            //  card in the list. But it works, so ¯\_(ツ)_/¯
        }
    }
}