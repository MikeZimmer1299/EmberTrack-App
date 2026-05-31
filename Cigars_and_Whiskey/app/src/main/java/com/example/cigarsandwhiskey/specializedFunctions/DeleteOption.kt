package com.example.cigarsandwhiskey.specializedFunctions

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.cigarsandwhiskey.dataAccessObjects.MyWhiskeyDao
import com.example.cigarsandwhiskey.objects.MyWhiskey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DeleteWhiskeyOption(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    deletedWhiskey: MyWhiskey?,
    scope: CoroutineScope,
    myWhiskeyDao: MyWhiskeyDao
){

    val icon = Icons.Rounded.Warning
    val whiskeyDelete: MyWhiskey = deletedWhiskey!!

    AlertDialog(
        title = {
            Text(text = dialogTitle, fontSize = 35.sp)
        },
        text = {
            Text(text = dialogText, fontSize = 24.sp, textAlign = TextAlign.Center, lineHeight = 30.sp)
        },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                    scope.launch {
                        try {
                            myWhiskeyDao.deleteWhiskey(whiskeyDelete)
                            Log.d("WhiskeyCollection", "Deleted Whiskey")
                        } catch (e: Exception){
                            Log.d("Database Error", "Unable to delete whiskey")
                        }
                    }
                }
            ) {
                Text("Confirm", color = Color.Red)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {onDismissRequest()}
            ) {
                Text("Cancel")
            }
        },
        containerColor = Color.White,
        iconContentColor = Color.Black,
        textContentColor = Color.Black,
        titleContentColor = Color.Red
    )
}