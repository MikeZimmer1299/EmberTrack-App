package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable
import com.example.cigarsandwhiskey.MainActivity

data class MyCigars(
    val cigarBrand: String,
    val cigarName: String,
    val sizeLength: Int,
    val ringGauge: Int,
    val humidor: String
)

class CigarMainActivity(){



    fun MainView(){

    }
}

@Composable
fun filterList(){

}

@Composable
fun MyCigarsScreen(){
    filterList()
}