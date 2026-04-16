package com.example.cigarsandwhiskey.objectInterface

import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.MyCigarsDao
import com.example.cigarsandwhiskey.dataAccessObjects.MyHumidorDao
import com.example.cigarsandwhiskey.dataAccessObjects.MyHumidorDao.*
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark

@Composable
fun MyCigarsScreen(
    navController: NavController,
    myCigarsDao: MyCigarsDao
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )

    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 30.dp, 0.dp, 0.dp),
        ) {
            Text(text = "My Cigars", fontSize = 40.sp, fontWeight = FontWeight.Bold,
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
                        15.dp,
                        10.dp, // right
                        5.dp
                    )
                    .fillMaxWidth()
                    .heightIn(160.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lushForestGrassLight
                )
            ) {
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ) {
                    Text(
                        text = cigars.cigarBrand,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 45.sp,
                        softWrap = true
                    )
                }
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ){
                    Text(
                        text = cigars.cigarName,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 45.sp,
                        softWrap = true
                    )
                }
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ) {
                    Text(
                        text = "Size: ${cigars.sizeLength} x ${cigars.ringGauge}",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 45.sp,
                        softWrap = true
                    )
                }
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ) {
                    Text(
                        text = "Quantity: ${cigars.quantity}",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 45.sp,
                        softWrap = true
                    )
                }
            }
        }

    }

    // TODO: This button will allow a user to add a new cigar(s) to their collection
    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(
                305.dp, // left
                920.dp,
                0.dp, // right
                0.dp
            ),
        containerColor = lushForestGrassLight,
        onClick = {
            navController.navigate("add_new_cigar")
        },
        icon = { Icon(Icons.Filled.Add, "Add Cigar Button") },
        text = { Text(text = "Add Cigar(s)")}
    )

    // TODO: Also need to think about how to add a humidor to be tracked
    //  This has been added to "later"

    ElevatedCard(modifier = Modifier
        .padding(20.dp)
        .height(20.dp)
    ) {
        // TIPS: Intentionally left blank. A terrible way to add spacing below the last
        //  card in the list. But it works, so ¯\_(ツ)_/¯
    }

}