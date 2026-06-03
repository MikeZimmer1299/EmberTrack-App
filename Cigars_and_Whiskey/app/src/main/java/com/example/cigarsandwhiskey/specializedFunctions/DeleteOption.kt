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
import com.example.cigarsandwhiskey.dataAccessObjects.CigarReviewDao
import com.example.cigarsandwhiskey.dataAccessObjects.MyCigarsDao
import com.example.cigarsandwhiskey.dataAccessObjects.MyWhiskeyDao
import com.example.cigarsandwhiskey.dataAccessObjects.WhiskeyReviewDao
import com.example.cigarsandwhiskey.objects.CigarReviews
import com.example.cigarsandwhiskey.objects.MyCigars
import com.example.cigarsandwhiskey.objects.MyWhiskey
import com.example.cigarsandwhiskey.objects.WhiskeyReviews
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

//    val icon = Icons.Rounded.Warning // will probably delete
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


@Composable
fun DeleteCigarOption(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    deletedCigar: MyCigars?,
    scope: CoroutineScope,
    myCigarDao: MyCigarsDao
){
    val cigarDelete: MyCigars = deletedCigar!!
// `!!` isn't best practice, BUT it will never be null, otherwise this function is never called

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
                            myCigarDao.deleteCigar(cigarDelete)
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


@Composable
fun DeleteCigarReviewOption(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    deletedReview: CigarReviews?,
    scope: CoroutineScope,
    cigarReviews: CigarReviewDao
){
    val reviewDelete: CigarReviews = deletedReview!!
// `!!` isn't best practice, BUT it will never be null, otherwise this function is never called

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
                            cigarReviews.deleteReview(reviewDelete)
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


@Composable
fun DeleteWhiskeyReviewOption(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    deletedReview: WhiskeyReviews?,
    scope: CoroutineScope,
    cigarReviews: WhiskeyReviewDao
){
    val reviewDelete: WhiskeyReviews = deletedReview!!
// `!!` isn't best practice, BUT it will never be null, otherwise this function is never called

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
                            cigarReviews.deleteReview(reviewDelete)
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