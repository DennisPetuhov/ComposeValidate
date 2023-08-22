package com.example.composevalidate

import android.widget.NumberPicker.OnValueChangeListener
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class new1 {
}

@Composable
fun ValidateCred() {
    var value: String by remember { mutableStateOf("") }
    val onTextChanged = { data: String -> value = data }
    var flag by remember { mutableStateOf(true) }

    MakeUi(value = value, onValueChange =onTextChanged, nameError = flag, onClick = {flag = value.length  < 5} )



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeUi(value: String, onValueChange: (String) -> Unit,nameError:Boolean, onClick: () -> Unit ) {
    Column() {
        TextField(value = value, onValueChange = onValueChange,isError = nameError) // Updates the UI based on the changes in the flag)
        Button(onClick = onClick) {

        }

    }

}