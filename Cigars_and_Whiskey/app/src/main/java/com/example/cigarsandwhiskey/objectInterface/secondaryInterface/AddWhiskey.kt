package com.example.cigarsandwhiskey.objectInterface.secondaryInterface

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.MyWhiskeyDao
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark
import kotlinx.coroutines.CoroutineScope

@Composable
fun AddWhiskey(
    navController: NavController,
    myWhiskeyDao: MyWhiskeyDao,
    scope: CoroutineScope
){

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ){
        Text(text = "This is the screen where I will add new whiskey to my collection")
    }

}