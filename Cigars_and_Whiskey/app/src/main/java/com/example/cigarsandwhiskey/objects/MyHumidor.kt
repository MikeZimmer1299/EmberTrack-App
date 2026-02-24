package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable
import com.example.cigarsandwhiskey.objects.MyCigars
import com.example.cigarsandwhiskey.objects.MyHumidor

val humidorList = listOf<MyHumidor>()

class MyHumidor(
    var humidorName: String,
    var cigarsInside: List<MyCigars> = listOf<MyCigars>(),
    var numberOfCigars: Int
) {

//    fun getCigars() : List<MyCigars>{
//        return cigarsInside
//    }

//    fun getHumidorName() : String{
//        return humidorName
//    }

//    fun getNumberOfCigars() : Int{
//        return numberOfCigars
//    }

}

// Getter for number of humidors
// May change once the static data is implemented
fun getMyHumidorList() : List<MyHumidor>{
    return humidorList
}

//@Composable
//fun testHumidor(){
//
//    var tempList: List<MyCigars> = listOf<MyCigars>()
//    var testHumidor = MyHumidor("Test", tempList)
//    var tempHumidorName: String
//
//    tempHumidorName = testHumidor.getHumidorName()
//}