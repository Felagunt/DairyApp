package com.example.diaryapp.lockScreen_feature.presentation.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun checkUserAuth(pin: List<Int>, onLoginSuccess: () -> Unit) {
    val isPinCorrect = pin == listOf(1, 2, 3, 4)
    if (isPinCorrect) {
        LaunchedEffect(Unit) {
            onLoginSuccess()
        }
    } else {
        Toast.makeText(
            LocalContext.current,
            "Login Failed",
            Toast.LENGTH_SHORT
        ).show()
    }
}