package com.example.composevalidate.ValidateManual

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composevalidate.R
import com.example.composevalidate.Stuff.InputErrors
import com.example.composevalidate.Stuff.InputValidator
import com.example.composevalidate.Stuff.InputWrapper
import com.example.composevalidate.Stuff.ScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
const val NAME = "name"
const val CREDIT_CARD_NUMBER = "ccNumber"
@HiltViewModel
class InputValidationManualViewModel @Inject constructor(
    private val handle: SavedStateHandle
) : ViewModel() {


    val name = handle.getStateFlow(NAME, InputWrapper())

    private val _events = Channel<ScreenEvent>()
    val events = _events.receiveAsFlow()
    fun onNameEntered(input: String) {
        handle[NAME] = name.value.copy(value = input, errorId = null)
    }


    fun onNameImeActionClick() {
//        _events.trySend(ScreenEvent.MoveFocus())
    }

    fun onContinueClick() {
        viewModelScope.launch(Dispatchers.Default) {
            when (val inputErrors = getInputErrorsOrNull()) {
                null -> {
//                    clearFocusAndHideKeyboard()
                    _events.send(ScreenEvent.ShowToast(R.string.success))
                }
                else -> displayInputErrors(inputErrors)
            }
        }
    }

    private fun getInputErrorsOrNull(): InputErrors? {
        val nameErrorId = InputValidator.getNameErrorIdOrNull(name.value.value)
        return if (nameErrorId == null ) {
            null
        } else {
            InputErrors(nameErrorId)
        }
    }

    private fun displayInputErrors(inputErrors: InputErrors) {
        handle[NAME] = name.value.copy(errorId = inputErrors.nameErrorId)
    }


}