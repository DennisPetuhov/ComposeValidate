package com.example.composevalidate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class New {
}
@Composable
fun ValidationsComposble(){

    var name by remember { mutableStateOf("") }
    val nameTextUpdate = { data : String ->
        name = data
    }
    var nameTextError by remember { mutableStateOf(false) } // Error Flag created

    ValidationsUI( name, nameTextUpdate, nameTextError ){
        nameTextError = name.length < 5
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValidationsUI(
    name : String,
    nameUpdate : (String) -> Unit,
    nameError : Boolean, // Error flag input
    validate : () -> Unit
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .verticalScroll(rememberScrollState())
    ){

        OutlinedTextField(
            value = name,
            onValueChange = nameUpdate,
            label = { Text("Name") },
            isError = nameError, // Updates the UI based on the changes in the flag
            modifier = Modifier
                .fillMaxWidth(),
        )


        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        Button(
            onClick = {
                validate()
            },
            shape = RoundedCornerShape(size = 22.5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
        ) {
            Text(
                text = "Submit",
                fontSize = 16.sp
            )
        }

    }

}