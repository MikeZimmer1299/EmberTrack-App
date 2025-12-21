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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cigarsandwhiskey.objectInterface.*
import kotlinx.coroutines.launch

@Composable
@Preview
fun Navigation(){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    ModalNavigationDrawer( // This is gesture based menu for different sections of app

        drawerContent = {
            ModalDrawerSheet {
                Text("Menu", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                NavigationDrawerItem(
                    label = {Text(text = "Home")},
                    selected = false,
                    onClick = {/* TODO */
                        scope.launch {
                            drawerState.close()
                            navController.navigate("home"){
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
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
            composable("home"){ HomeScreen() }
            composable("my_cigars"){ MyCigarsScreen() }
            composable("my_whiskey") { MyWhiskeyScreen() }
            composable("cigar_brands"){ CigarBrandsScreen() }
            composable("whiskey_brands"){ WhiskeyBrandsScreen() }
            composable("cigar_reviews"){ CigarReviewsScreen() }
            composable("whiskey_reviews"){ WhiskeyReviewsScreen() }
        }
    }


}