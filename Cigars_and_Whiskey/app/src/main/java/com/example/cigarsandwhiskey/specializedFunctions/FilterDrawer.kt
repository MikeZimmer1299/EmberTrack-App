package com.example.cigarsandwhiskey.specializedFunctions

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

import com.example.cigarsandwhiskey.generalFunctions.DropdownMenu
import com.example.cigarsandwhiskey.generalFunctions.InputTextField
import com.example.cigarsandwhiskey.generalFunctions.cigarBrandsList
import com.example.cigarsandwhiskey.generalFunctions.cigarOriginList
import com.example.cigarsandwhiskey.generalFunctions.whiskeyBrandsList
import com.example.cigarsandwhiskey.generalFunctions.whiskeyOriginList

/*
* TIPS: Below is a new concept: Slot API
*   This is going to pass the open drawer trigger to the screen.
*   The drawer is going to wrap the screen it sits on top of, in this case
*   it is the CigarReviews screen.
*/
@Composable
fun CigarReviewFilter(
    onFilterChange: (FilterType, String) -> Unit,
    // TIPS: Accepts composable screen and provides a trigger to open the drawer.
    //  `openFilter` is its own function, so the `()` is able to take params
    content: @Composable (openFilter: () -> Unit) -> Unit
){

    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var filterQuery by remember { mutableStateOf("") }
    var cigarBrandQuery by remember { mutableStateOf("") }
    var cigarNameQuery by remember { mutableStateOf("") }
    var cigarOriginQuery by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        // swipe for navigation still works with this added, and it becomes only swipe to close
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(
                drawerContentColor = Color.White,
                drawerContainerColor = Color.Black
            ) {
                // TODO: Add filter content
                Text(
                    text = "Filter",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White,
                    fontSize = dynamicFontSize * .6f
                )
                HorizontalDivider()

                // TODO: Will return later to potentially clean up the design and
                //  change its implementation to be better in general. It serves its purpose as
                //  it sits now.
                Button(
                    onClick = {
                        onFilterChange(FilterType.ALL, "")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Reset")
                }
                Row(){
                    val cigarBrandsList = cigarBrandsList()
                    DropdownMenu(
                        itemList = cigarBrandsList,
                        selectedText = cigarBrandQuery.ifEmpty { "Choose a Band" },
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        onItemClick = {cigarBrandQuery = cigarBrandsList[it]}
                    )
                }
                Button(
                    onClick = {
                        if(cigarBrandQuery.isEmpty()){
                            // do nothing
                        } else {
                            onFilterChange(FilterType.BRAND, cigarBrandQuery)
                            scope.launch { drawerState.close() }
                        }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Brand")
                }
                Row(){
                    InputTextField(
                        cigarNameQuery,
                        onTextChange = {cigarNameQuery = it},
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        placeholder = "Enter Cigar Name"
                    )
                }
                Button(
                    onClick = {
                        if(cigarNameQuery.isEmpty()){
                            // Do nothing
                        } else {
                            onFilterChange(FilterType.NAME, cigarNameQuery)
                            scope.launch { drawerState.close() }
                        }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Name")
                }
                Row(){
                    val cigarOriginsList = cigarOriginList()
                    DropdownMenu(
                        itemList = cigarOriginsList,
                        selectedText = cigarOriginQuery.ifEmpty { "Country of Origin" },
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        onItemClick = {cigarOriginQuery = cigarOriginsList[it]}
                    )
                }
                Button(
                    onClick = {
                        if(cigarOriginQuery.isEmpty()){
                            // do nothing
                        } else {
                            onFilterChange(FilterType.COUNTRY, cigarOriginQuery)
                            scope.launch { drawerState.close() }
                        }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Country")
                }
            }
        }
    ) {
        // TIPS: Draws main screen, then allows coroutine logic to open the drawer
        content {
            filterQuery = ""
            cigarBrandQuery = ""
            cigarNameQuery = ""
            cigarOriginQuery = ""
            scope.launch { drawerState.open() }
        }

    }
}


// TIPS: Same for above, but for Whiskey Reviews
@Composable
fun WhiskeyReviewFilter(
    onFilterChange: (FilterType, String) -> Unit,
    content: @Composable (openFilter: () -> Unit) -> Unit
){

    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var filterQuery by remember { mutableStateOf("") }
    var whiskeyBrandQuery by remember { mutableStateOf("") }
    var whiskeyNameQuery by remember { mutableStateOf("") }
    var whiskeyOriginQuery by remember { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        // swipe for navigation still works with this added, and it becomes only swipe to close
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(
                drawerContentColor = Color.White,
                drawerContainerColor = Color.Black
            ) {
                // TODO: Add filter content
                Text(
                    text = "Filter",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White,
                    fontSize = dynamicFontSize * .6f
                )
                HorizontalDivider()

                Button(
                    onClick = {
                        onFilterChange(FilterType.ALL, "")
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Reset")
                }
                Row(){
                    val whiskeyBrandsList = whiskeyBrandsList()
                    DropdownMenu(
                        itemList = whiskeyBrandsList(),
                        selectedText = whiskeyBrandQuery.ifEmpty { "Choose a Band" },
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        onItemClick = {whiskeyBrandQuery = whiskeyBrandsList[it]}
                    )
                }
                Button(
                    onClick = {
                        if(whiskeyBrandQuery.isEmpty()){
                            // do nothing
                        } else {
                            onFilterChange(FilterType.BRAND, whiskeyBrandQuery)
                            scope.launch { drawerState.close() }
                        }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Brand")
                }
                Row(){
                    InputTextField(
                        whiskeyNameQuery,
                        onTextChange = {whiskeyNameQuery = it},
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        placeholder = "Enter Whiskey Name"
                    )
                }
                Button(
                    onClick = {
                        if(whiskeyNameQuery.isEmpty()){
                            // do nothing
                        } else {
                            onFilterChange(FilterType.NAME, whiskeyNameQuery)
                            scope.launch { drawerState.close() }
                        }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Name")
                }
                Row(){
                    val whiskeyOriginsList = whiskeyOriginList()
                    DropdownMenu(
                        itemList = whiskeyOriginsList,
                        selectedText = whiskeyOriginQuery.ifEmpty { "Country of Origin" },
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        onItemClick = {whiskeyOriginQuery = whiskeyOriginsList[it]}
                    )
                }
                Button(
                    onClick = {
                        if(whiskeyOriginQuery.isEmpty()){
                            // do nothing
                        } else {
                            onFilterChange(FilterType.COUNTRY, whiskeyOriginQuery)
                            scope.launch { drawerState.close() }
                        }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Country")
                }
            }
        }
    ) {
        // TIPS: Draws main screen, then allows coroutine logic to open the drawer
        content {
            filterQuery = ""
            whiskeyBrandQuery = ""
            whiskeyNameQuery = ""
            whiskeyOriginQuery = ""
            scope.launch { drawerState.open() }
        }
    }
}