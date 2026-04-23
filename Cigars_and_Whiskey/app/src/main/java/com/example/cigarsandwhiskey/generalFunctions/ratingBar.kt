package com.example.cigarsandwhiskey.generalFunctions

import android.R.attr.maxWidth
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ratingBar(
    rating: Float,
    maxRating: Int = 10,
    onRatingChanged: (Float) -> Unit
){
    Row {
        for (i in 1..maxRating) {

            val isFilled = i <= rating

            BoxWithConstraints(
                modifier = Modifier
                    .padding(
                        2.dp,
                        10.dp,
                        2.dp,
                        2.dp
                    )
                    .weight(1f)
                    .aspectRatio(0.5f)
                    .background(
                        if (isFilled) Color(0xFF4CAF50)
                            else Color(0xFF7A4343),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        onRatingChanged(i.toFloat())
                    },
                contentAlignment = Alignment.Center,
            ){
                val dynamicFontSize = (maxWidth.value * 0.5f).sp
                Text(text = i.toString(), fontWeight = FontWeight.Bold, fontSize = dynamicFontSize)
            }
        }
    }
}