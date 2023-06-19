package com.example.composevalidate.ValidateHard.Stuff

import android.content.Context
import android.widget.Toast

fun Context.toast(messageId: Int) {
    Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show()
}