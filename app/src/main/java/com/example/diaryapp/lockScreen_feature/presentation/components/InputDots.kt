package com.example.diaryapp.lockScreen_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun InputDots(
    numbers: List<Int> = listOf(1, 2),
) {
    //2nd way
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        PinIndicator(filled = numbers.isNotEmpty())
        PinIndicator(filled = numbers.size > 1)
        PinIndicator(filled = numbers.size > 2)
        PinIndicator(filled = numbers.size > 3)
    }
}