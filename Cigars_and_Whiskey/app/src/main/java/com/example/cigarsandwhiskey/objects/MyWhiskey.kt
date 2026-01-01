package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable

data class MyWhiskey(
    val brand: String,
    val name: String,
    val proof: Float
)

@Composable
// param proof = the proof of the chosen whiskey to be converted
fun proofToPercent(proof: Float): Float {
    return (proof / 2);
}