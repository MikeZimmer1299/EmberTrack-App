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
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

import com.example.cigarsandwhiskey.dataAccessObjects.WhiskeyReviewDao
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark

@Composable
fun WhiskeyReviewsScreen(
    navController: NavController,
    whiskeyReviewDao: WhiskeyReviewDao
    ){

    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

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
            Text(text = "Whiskey Reviews", fontSize = dynamicFontSize * 1.4f, fontWeight = FontWeight.Bold,
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

        // TODO: First card with whiskey brand, name, type, and proof
        val reviewList by whiskeyReviewDao.getAllWhiskeyReviews()
            .collectAsStateWithLifecycle(emptyList())

        Log.d("Output", "whiskeyReviewDao.getAllWhiskeyReviews() success")

        reviewList.forEach { reviews ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(160.dp) // min height is 160.dp
                    .padding(
                        10.dp, // left
                        20.dp,
                        10.dp, // right
                        0.dp),
                colors = CardDefaults.cardColors(containerColor = lushForestGrassLight)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ) {
                    Text(
                        text = reviews.brand,
                        fontSize = dynamicFontSize * 1.2f,
                        fontWeight = FontWeight.Bold,
                        lineHeight = dynamicFontSize * 1.2f, // prevents text overlap when wrapping text
                        softWrap = true
                    )
                }
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ) {
                    Text(
                        text = reviews.whiskeyName,
                        fontSize = dynamicFontSize * 1.2f,
                        fontWeight = FontWeight.Bold,
                        lineHeight = dynamicFontSize * 1.2f, // prevents text overlap when wrapping text
                        softWrap = true
                    )
                }
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ){
                    Text(
                        text = "Proof: ${reviews.proof} / " + reviews.proof.toFloat() / 2 + "%",
                        fontSize = dynamicFontSize * 1.1f,
                        fontWeight = FontWeight.Bold,
                        lineHeight = dynamicFontSize * 1.2f, // prevents text overlap when wrapping text
                        softWrap = true
                    )
                }
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp)
                ){
                    Text(
                        text = "Overall Score: ${reviews.overallScore}",
                        fontSize = dynamicFontSize * 1.1f,
                        fontWeight = FontWeight.Bold,
                        lineHeight = dynamicFontSize * 1.2f, // prevents text overlap when wrapping text
                        softWrap = true
                    )
                }
            }
            Log.d("Output", "ReviewList has added a review")
        }
        ElevatedCard(modifier = Modifier
            .padding(20.dp)
            .height(20.dp)
        ) {
            // TIPS: Intentionally left blank. A terrible way to add spacing below the last
            //  card in the list. But it works, so ¯\_(ツ)_/¯
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////

    // TIPS: This button will allow a user to add a new whiskey review
    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(
                310.dp, // left
                920.dp,
                0.dp, // right
                0.dp
            ),
        containerColor = lushForestGrassLight,
        onClick = { navController.navigate("new_whiskey_review")},
        icon = { Icon(Icons.Filled.Edit, "Add Whiskey Review Button") },
        text = { Text(text = "Add Review", fontSize = dynamicFontSize * .47f)}
    )

}