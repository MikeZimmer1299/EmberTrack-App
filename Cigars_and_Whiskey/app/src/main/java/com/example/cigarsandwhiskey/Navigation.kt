package com.example.cigarsandwhiskey

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cigarsandwhiskey.objectInterface.*
import com.example.cigarsandwhiskey.objectInterface.secondaryInterface.*
//import com.example.cigarsandwhiskey.objects.MyHumidor.*
import kotlinx.coroutines.launch

@Composable
fun Navigation(database: AppDatabase){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer( // This is gesture based menu for different sections of app

        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Menu",
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = {Text(text = "Home")},
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            navController.navigate("home"){
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                                /*
                                * The above section is what *clears* the history of different tabs.
                                * When traversing to the home screen, I think this is a good idea,
                                * but when traversing to other screens, a person's first instinct
                                * is to press the back button on their phone when they want to
                                * return to a previous screen, not use the navigation drawer when
                                * they want to go back to the previous screen.
                                * Or I may keep it for the main screens, then leave these lines of
                                * code out of the `onClick` like when adding a review or cigars.
                                */
                            }
                        }
                    }
                )
                NavigationDrawerItem(
                    label = {Text(text = "My Cigars")},
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            navController.navigate("my_cigars"){
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
                NavigationDrawerItem(
                    label = {Text(text = "My Whiskey")},
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            navController.navigate("my_whiskey"){
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
                NavigationDrawerItem(
                    label = {Text(text = "Cigar Brands")},
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            navController.navigate("cigar_brands"){
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
                NavigationDrawerItem(
                    label = {Text(text = "Whiskey Brands")},
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            navController.navigate("whiskey_brands"){
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
                NavigationDrawerItem(
                    label = {Text(text = "Cigar Reviews")},
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            navController.navigate("cigar_reviews"){
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
                NavigationDrawerItem(
                    label = {Text(text = "Whiskey Reviews")},
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            navController.navigate("whiskey_reviews"){
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        },
        gesturesEnabled = true
    ) {

        NavHost(
            navController = navController,
            startDestination = "home"
        ){
            // TODO: Need to add `database.___` as param to view info from database
            // Drawer Screen Options
            composable("home"){ HomeScreen(database) }
//            composable("my_humidors") { HumidorOptionScreen(navController, database.myHumidorsDao()) } // removed for now, may implement at a later time
            composable("my_cigars"){ MyCigarsScreen(navController, database.myCigarsDao()) } // add
            composable("my_whiskey") { MyWhiskeyScreen(navController, database.myWhiskeyDao(), scope) } // add
            composable("cigar_brands"){ CigarBrandsScreen() }
            composable("whiskey_brands"){ WhiskeyBrandsScreen() }
            composable("cigar_reviews"){ CigarReviewsScreen(navController, database.cigarReviewDao()) } // add
            composable("whiskey_reviews"){ WhiskeyReviewsScreen(navController, database.myWhiskeyReviewDao()) } // add

            // Buttons from within different screens
            composable("add_new_cigar"){ AddCigars(navController, database.myCigarsDao(), scope) }
            composable("add_new_whiskey") { AddWhiskey(navController, database.myWhiskeyDao(), scope) }
            composable("new_cigar_review"){ NewCigarReview(navController, database.cigarReviewDao(), scope) }
            composable("new_whiskey_review"){ NewWhiskeyReview(navController, database.myWhiskeyReviewDao(), scope) }
        }
    }


}