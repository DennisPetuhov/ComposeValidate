package com.example.composevalidate.ValidateManual

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.composevalidate.R
import com.example.composevalidate.Stuff.CustomTextField
import com.example.composevalidate.Stuff.ScreenEvent
import com.example.composevalidate.Stuff.toast

@Composable
fun InputValidationManualScreen(
    viewModel: InputValidationManualViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val name by viewModel.name.collectAsStateWithLifecycle()
    val events = remember(viewModel.events, lifecycleOwner) {
        viewModel.events.flowWithLifecycle(
            lifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        )
    }

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is ScreenEvent.ShowToast -> context.toast(event.messageId)
//                is ScreenEvent.UpdateKeyboard -> {
//                    if (event.show) keyboardController?.show() else keyboardController?.hide()
//                }
//                is ScreenEvent.ClearFocus -> focusManager.clearFocus()
//                is ScreenEvent.RequestFocus -> {
//                    when (event.textFieldKey) {
//                        FocusedTextFieldKey.NAME -> nameFocusRequester.requestFocus()
//                        FocusedTextFieldKey.CREDIT_CARD_NUMBER -> creditCardNumberFocusRequester.requestFocus()
//                        else -> {}
//                    }
//                }
//                is ScreenEvent.MoveFocus -> focusManager.moveFocus(event.direction)
                else -> {}
            }
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTextField(
            modifier = Modifier,
//
            labelResId = R.string.name,
//
            inputWrapper = name,
            onValueChange = viewModel::onNameEntered,
            onImeKeyAction = viewModel::onNameImeActionClick
        )
        Spacer(Modifier.height(16.dp))

        Spacer(Modifier.height(32.dp))
        Button(onClick = viewModel::onContinueClick) {
            Text(text = "Continue")
        }
    }
}