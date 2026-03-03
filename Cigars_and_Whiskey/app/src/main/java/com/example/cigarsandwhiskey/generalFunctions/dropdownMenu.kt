package com.example.cigarsandwhiskey.generalFunctions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties


// I think I have this to be able to be universal for any lists
@Composable
fun DropdownMenu(
    itemList: List<String>,
    selectedIndex: Int,
    initialText: String,
    onItemClick: (Int) -> Unit
){
    var showDropdown by rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var selectedText by rememberSaveable { mutableStateOf(initialText)}

    // TIPS: This is the BUTTON OBJECT, which will allow the menu to open
    Box(
        modifier = Modifier
            .background(Color.White)
            .clickable{showDropdown = !showDropdown}
            .width(220.dp)
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.Center
    ){
        Text(text = selectedText, modifier = Modifier.padding(5.dp))
    }


    // TIPS: The Dropdown List itself
    Box(){
        if (showDropdown){
            Popup(
                alignment = Alignment.TopCenter,
                properties = PopupProperties(
                    excludeFromSystemGesture = true
                ),
                onDismissRequest = {showDropdown = false}
            ) {
                Column(
                    modifier = Modifier
                        .heightIn(max = 250.dp)
                        .verticalScroll(state = scrollState)
                        .border(width = 1.dp, color = Color.Gray),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    itemList.onEachIndexed { index, item ->
                        Box(
                            modifier = Modifier
                                .background(Color.LightGray)
                                .width(350.dp)
                                .height(50.dp)
                                .clickable{
                                    selectedText = item
                                    onItemClick(index)
                                    showDropdown = !showDropdown
                                },
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = item, fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }

}

fun CigarBrandsList(): List<String>{

    val brandList = listOf<String>(
        "Tatuaje",
        "Davidoff",
        "Cavalier Geneve",
        "Crowned Heads",
        "EGM"
    )

    return brandList
}

fun CigarOriginList(): List<String>{

    val originList = listOf<String>(
        "Dominican Republic",
        "Nicaragua",
        "Honduras",
        "United States",
        "Cuba"
    )

    return originList
}












