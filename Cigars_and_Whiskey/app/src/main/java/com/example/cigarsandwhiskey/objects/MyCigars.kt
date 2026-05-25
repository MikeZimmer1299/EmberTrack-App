package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.cigarsandwhiskey.MainActivity

@Entity(tableName = "my_cigars")
data class MyCigars(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

//    var humidorId: Int = 0, // default to "Main Humidor"
    var cigarBrand: String = "",
    var cigarName: String = "",
    var countryOfOrigin: String = "",
    var sizeLength: String = "",
    var ringGauge: String = "",
    var quantity: String = ""
){

}