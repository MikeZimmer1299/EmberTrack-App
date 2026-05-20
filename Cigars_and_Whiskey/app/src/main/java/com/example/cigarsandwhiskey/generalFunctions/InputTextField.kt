package com.example.cigarsandwhiskey.generalFunctions

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
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
    modifier: Modifier = Modifier,
    designatedKeyboard: KeyboardType = KeyboardType.Text
        // Defaults to text keyboard, but for text fields with intended ints,
        //  I can pass `KeyboardType.Number` to prevent text from being entered by user
){
    val focusManager = LocalFocusManager.current

    var parentWidth by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(4.dp))
                .fillMaxWidth()
                .height(45.dp)
                .border(3.dp, Color.Black)
                .onSizeChanged { parentWidth = it.width }
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

            if (text.isEmpty()) {
                Text(text = placeholder, color = Color.Black,
//                    fontSize = 20.sp
                    fontSize = (LocalConfiguration.current.screenWidthDp * 0.043f).sp
                )
            }
        }
    }
}