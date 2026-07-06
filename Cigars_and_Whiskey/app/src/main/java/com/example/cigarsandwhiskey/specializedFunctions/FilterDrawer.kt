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
    var cigarNameQuery by remember { mutableStateOf("") }

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
                // TODO: 3 main filters:
                //  Cigar brand, based on dropdown list
                //  Cigar name, based on user's text input
                //  Country of Origin, based on dropdown list

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
                        initialText = "Choose a Band",
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        onItemClick = {filterQuery = cigarBrandsList[it]}
                    )
                }
                Button(
                    onClick = {
                        onFilterChange(FilterType.BRAND, filterQuery)
                        scope.launch { drawerState.close() }
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
                        onFilterChange(FilterType.NAME, cigarNameQuery)
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Name")
                }
                Row(){
                    val cigarOriginsList = cigarOriginList()
                    DropdownMenu(
                        itemList = cigarOriginsList,
                        initialText = "Country of Origin",
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        onItemClick = {filterQuery = cigarOriginsList[it]}
                    )
                }
                Button(
                    onClick = {
                        onFilterChange(FilterType.COUNTRY, filterQuery)
                        scope.launch { drawerState.close() }
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
    var whiskeyNameQuery by remember { mutableStateOf("") }

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
                // TODO: 3 main filters:
                //  Whiskey brand, based on dropdown list
                //  Whiskey name, based on user's text input
                //  Country of Origin, based on dropdown list

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
                        initialText = "Choose a Band",
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        onItemClick = {filterQuery = whiskeyBrandsList[it]}
                    )
                }
                Button(
                    onClick = {
                        onFilterChange(FilterType.BRAND, filterQuery)
                        scope.launch { drawerState.close() }
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
                        onFilterChange(FilterType.NAME, whiskeyNameQuery)
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.width((screenWidth * .24f).dp)
                ) {
                    Text(text = "Name")
                }
                Row(){
                    val whiskeyOriginsList = whiskeyOriginList()
                    DropdownMenu(
                        itemList = whiskeyOriginsList,
                        initialText = "Country of Origin",
                        modifier = Modifier.weight(screenWidth * .0011f).padding(5.dp, 3.dp),
                        onItemClick = {filterQuery = whiskeyOriginsList[it]}
                    )
                }
                Button(
                    onClick = {
                        onFilterChange(FilterType.COUNTRY, filterQuery)
                        scope.launch { drawerState.close() }
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
            scope.launch { drawerState.open() }
        }
    }
}