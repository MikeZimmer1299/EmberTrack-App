package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable
import com.example.cigarsandwhiskey.MainActivity

data class MyCigars(
    var cigarBrand: String,
    var cigarName: String,
    var countryOfOrigin: String,
    var sizeLength: Float,
    var ringGauge: Int
    // TODO: At later time, may decide to return all data classes to be their own classes
    //  Unknown, as of right now, if MyCigars best left as data class or an entire class with
    //  included functions
    // The main issue is simply because if I need to call an object inside the screen, I will
    //  to import the package into the screen files.
    // I have removed the humidor var, created a MyHumidor object that will
    //  properly track the cigars
){
    // Used to display the cigar in plaintext
    override fun toString(): String { // TODO:

        var cigarFormat: String
        cigarFormat = "$cigarBrand $cigarName, $sizeLength x $ringGauge"

        return cigarFormat
    }

    // TODO:
}

class CigarMainActivity(){

    // I think I can technically remove this function, since now all screens
    //  have their own files to handle the display (and eventually handle all accessed data)

}

@Composable
fun filterList(list: List<MyCigars>){

}

@Composable
fun MyCigarsScreen(){
//    filterList()
}