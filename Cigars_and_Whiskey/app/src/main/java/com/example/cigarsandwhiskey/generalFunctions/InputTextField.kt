package com.example.cigarsandwhiskey.generalFunctions

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Reusable text field that allows the user to input text for different pieces of information,
//  such as cigar name,
@Composable
fun InputTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    designatedKeyboard: KeyboardType = KeyboardType.Text
        // Defaults to text keyboard, but for text fields with intended ints,
        //  I can pass `KeyboardType.Number` to prevent text from being entered by user
){
    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier
            .background(Color.White)
            .width(220.dp)
            .height(40.dp)
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = designatedKeyboard,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            )
        )

        if(text.isEmpty()){
            Text(text = placeholder, color = Color.Black, fontSize = 20.sp)
        }
    }
}