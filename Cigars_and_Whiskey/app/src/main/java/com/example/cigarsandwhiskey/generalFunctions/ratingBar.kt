package com.example.cigarsandwhiskey.generalFunctions

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
import androidx.compose.ui.unit.dp

@Composable
fun ratingBar(
    rating: Float,
    maxRating: Int = 10,
    onRatingChanged: (Float) -> Unit
){
    Row {
        for (i in 1..maxRating) {

            val isFilled = i <= rating

            Box(
                modifier = Modifier
                    .padding(
                        2.dp,
                        20.dp,
                        2.dp,
                        2.dp
                    )
                    .size(width = 42.dp, height = 86.dp)
                    .background(
                        if (isFilled) Color(0xFF4CAF50)
                        else Color(0xFF7A4343),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable {
                        onRatingChanged(i.toFloat())
                    },
                contentAlignment = Alignment.Center
            ){
                Text(text = i.toString())
            }
        }
    }
}