package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.MyCigarsDao
import com.example.cigarsandwhiskey.objects.MyCigars
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark
import kotlinx.coroutines.CoroutineScope

@Composable
fun AddCigars(
    navController: NavController,
    myCigarsDao: MyCigarsDao,
    scope: CoroutineScope
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
//        Text(text = "This is the screen where I will add new cigars to my collection")

        // TODO: Cigar Brand
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    45.dp,
                    10.dp, // right
                    5.dp
                )
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Text(text = "Where does this get formatted?", fontSize = 25.sp)
        }

        // TODO: Cigar Name
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ){

        }

        // TODO: Country of Origin
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ){

        }

        // TODO Length
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ){

        }

        // TODO: Ring Gauge
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ){

        }

        // TODO: Humidor
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ){

        }

        // TODO: Quantity
        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    65.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ){

        }

    }

}