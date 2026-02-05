package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable

data class MyWhiskey(
    var brand: String,
    var name: String,
    var proof: Float
)

@Composable
// param proof = the proof of the chosen whiskey to be converted
// When entering a float, remember to add `f` at the end if a hard int or `.toFloat()` if val/var
fun proofToPercent(proof: Float): Float {
    return (proof / 2);
}