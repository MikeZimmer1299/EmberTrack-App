package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.cigarsandwhiskey.objects.MyCigars
import com.example.cigarsandwhiskey.objects.MyHumidor

val humidorList = listOf<MyHumidor>()

@Entity(tableName = "my_humidors")
data class MyHumidor(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var humidorName: String = "Main Humidor",
    var numberOfCigars: Int = 0
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

data class HumidorWithCigars(
    @Embedded
    val humidor: MyHumidor,

    @Relation(
        parentColumn = "id",
        entityColumn = "humidorId"
    )
    val cigarsInside: List<MyCigars>
)

// Getter for number of humidors
// May change once the static data is implemented
fun getMyHumidorSize() : Int{
    return humidorList.size
}

// Getter for list of user's humidors
fun getMyHumidorList() : List<MyHumidor>{
    return humidorList
}