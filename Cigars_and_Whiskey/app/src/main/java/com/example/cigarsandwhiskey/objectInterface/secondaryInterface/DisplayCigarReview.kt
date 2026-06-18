package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.CigarReviewDao
import com.example.cigarsandwhiskey.objects.CigarReviews
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark

@Composable
fun DisplayCigarReview(
    navController: NavController,
    id: Int,
    cigarReviewDao: CigarReviewDao
){
    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    Box(modifier = Modifier.fillMaxSize()){
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .verticalScroll(rememberScrollState()),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGreenDark
            )
        ) {
            // TIPS: Title for Screen
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 30.dp, 0.dp, 0.dp),
            ) {
                Text(
                    text = "Cigar Review",
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

            ///////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////

            val reviewToShow by remember(id) {
                cigarReviewDao.getDisplayCigarReview(id)
            }.collectAsState(initial = null)
            Log.d("Output", "cigarReviewDao.getDisplayCigarReview() success")

            // TODO: Now to figure out how to format the data to be visually pleasing.
            //  The order is going to be the same as new cigar reviews.
            //  The hard part is the design, similar but not copy-pasted


            // TODO: Eventually, I will have the user be able to have a written review section
            //  which will then be displayed here in a text box
        }
    }
}