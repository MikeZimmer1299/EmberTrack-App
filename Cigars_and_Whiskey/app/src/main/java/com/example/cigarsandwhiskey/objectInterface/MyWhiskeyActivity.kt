package com.example.cigarsandwhiskey.objectInterface

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.cigarsandwhiskey.dataAccessObjects.MyWhiskeyDao
import com.example.cigarsandwhiskey.objects.MyWhiskey
import com.example.cigarsandwhiskey.specializedFunctions.DeleteWhiskeyOption
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyWhiskeyScreen(
    navController: NavController,
    myWhiskeyDao: MyWhiskeyDao,
    scope: CoroutineScope
){

    // TIPS: Dynamic Screen Size Variables
    val screenConfig = LocalConfiguration.current
    val screenWidth = screenConfig.screenWidthDp
    val dynamicFontSize = (screenWidth * 0.072f).sp

    var deleteWhiskey by remember { mutableStateOf(false) }
    var whiskeyToDelete by remember { mutableStateOf<MyWhiskey?>(null) }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 30.dp, 0.dp, 0.dp),
        ) {
            Text(
                text = "My Whiskey", fontSize = dynamicFontSize * 1.4f, fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .drawBehind {
                        val strokeWidthPx = 3.dp.toPx()
                        drawLine(
                            color = Color.Black,
                            strokeWidth = strokeWidthPx,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height)
                        )
                    }
            )
        }

            val myWhiskeyList by myWhiskeyDao.getAllWhiskey()
                .collectAsStateWithLifecycle(emptyList())
            Log.d("Output", "myWhiskeyDao.getAllWhiskey() success")

            myWhiskeyList.forEach { whiskey ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(
                            10.dp, // left
                            15.dp,
                            10.dp, // right
                            5.dp
                        )
                        .fillMaxWidth()
                        .heightIn(160.dp)
                        .combinedClickable(
                            onClick = {
//                                whiskeyToDelete = whiskey
                            },
                            onLongClick = {
                                // TODO: This long click will allow the user to delete a whiskey
//                                myWhiskeyDao.deleteWhiskey(whiskey)
                                whiskeyToDelete = whiskey
                                deleteWhiskey = true
                            }
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGrassLight
                    )
                ){
                    Row(
                        modifier = Modifier.padding(10.dp,0.dp)
                    ) {
                        Text(
                            text = whiskey.brand,
                            fontSize = dynamicFontSize * 1.2f,
                            fontWeight = FontWeight.Bold,
                            lineHeight = dynamicFontSize * 1.2f,
                            softWrap = true
                        )
                    }
                    Row(
                        modifier = Modifier.padding(10.dp,0.dp)
                    ) {
                        Text(
                            text = whiskey.name,
                            fontSize = dynamicFontSize * 1.2f,
                            fontWeight = FontWeight.Bold,
                            lineHeight = dynamicFontSize * 1.2f,
                            softWrap = true
                        )
                    }
                    Row(
                        modifier = Modifier.padding(10.dp,0.dp)
                    ) {
                        Text(
                            text = "Proof: ${whiskey.proof}",
                            fontSize = dynamicFontSize * 1.1f,
                            fontWeight = FontWeight.Bold,
                            lineHeight = dynamicFontSize * 1.2f,
                            softWrap = true
                        )
                    }
                    Row(
                        modifier = Modifier.padding(10.dp,0.dp)
                    ) {
                        Text(
                            text = "Age Statement: ${whiskey.ageStatement}",
                            fontSize = dynamicFontSize * 1.1f,
                            fontWeight = FontWeight.Bold,
                            lineHeight = dynamicFontSize * 1.2f,
                            softWrap = true
                        )
                    }
                }
            }

            ElevatedCard(modifier = Modifier
                .padding(20.dp)
                .height(20.dp)
            ) {
                // TIPS: Intentionally left blank. A terrible way to add spacing below the last
                //  card in the list. But it works, so ¯\_(ツ)_/¯
            }
        }

    if(deleteWhiskey){
        DeleteWhiskeyOption(
            onDismissRequest = {deleteWhiskey = false},
            onConfirmation = {
                deleteWhiskey = false
            },
            dialogTitle = "Delete Whiskey",
            dialogText = "Are you sure you want to delete this whiskey from your collection?",
            whiskeyToDelete,
            scope,
            myWhiskeyDao
        )
    }


    // TIPS: Allows the user to add whiskey to their collection
    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(
                303.dp, // left
                920.dp,
                0.dp, // right
                0.dp
            ),
        containerColor = lushForestGrassLight,
        onClick = {
            navController.navigate("add_new_whiskey")
        },
        icon = { Icon(Icons.Filled.Add, "Add Whiskey Button") },
        text = { Text(text = "Add Whiskey", fontSize = dynamicFontSize * .47f)}
    )
}