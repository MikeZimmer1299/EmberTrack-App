package com.example.cigarsandwhiskey.generalFunctions

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cigarsandwhiskey.objects.CigarReviews
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight

@Composable
fun CigarReviewCards(cigarReview: CigarReviews){


    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(160.dp) // min height is 150.dp
            .padding(
                10.dp, // left
                20.dp,
                10.dp, // right
                0.dp),
        colors = CardDefaults.cardColors(containerColor = lushForestGrassLight)
    ){
        Row(
            modifier = Modifier.padding(10.dp, 0.dp)
        ) {
            Text(
                text = cigarReview.brand,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 45.sp, // prevents text overlap when wrapping text
                softWrap = true
            )
        }
        Row(
            modifier = Modifier.padding(10.dp, 0.dp)
        ) {
            Text(
                text = cigarReview.cigarName,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 45.sp, // prevents text overlap when wrapping text
                softWrap = true
            )
        }
        Row(
            modifier = Modifier.padding(12.dp, 0.dp)
        ) {
            Text(
                text = "Final Score: ${cigarReview.finalScore}",
                fontSize = 35.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 35.sp
            )
        }
    }


}