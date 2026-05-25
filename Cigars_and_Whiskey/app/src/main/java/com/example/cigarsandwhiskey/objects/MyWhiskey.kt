package com.example.cigarsandwhiskey.objects

import androidx.compose.runtime.Composable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_whiskey")
data class MyWhiskey(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var brand: String = "",
    var name: String = "",
    var proof: String = "",
    var type: String = "",
    var origin: String = "",
    var ageStatement: String = "",
    var bottleSize: String = ""
    // Do I add quantity of bottles?
    // People commonly get more than one bottle at a time
)