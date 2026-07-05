package com.example.cigarsandwhiskey.specializedFunctions

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

/*
* TIPS: Below is a new concept: Slot API
*   This is going to pass the open drawer trigger to the screen.
*   The drawer is going to wrap the screen it sits on top of, in this case
*   it is the CigarReviews screen.
*/
@Composable
fun CigarReviewFilter(
    // TIPS: Accepts composable screen and provides a trigger to open the drawer.
    //  `openFilter` is its own function, so the `()` is able to take params
    content: @Composable (openFilter: () -> Unit) -> Unit
){

    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
    content: @Composable (openFilter: () -> Unit) -> Unit
){

    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
            }
        }
    ) {
        // TIPS: Draws main screen, then allows coroutine logic to open the drawer
        content {
            scope.launch { drawerState.open() }
        }

    }
}