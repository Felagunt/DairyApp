package com.example.diaryapp.lockScreen_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PinIndicator(
    filled: Boolean,
) {
    Box(
        modifier = Modifier
            .padding(15.dp)
            .size(15.dp)
            .clip(CircleShape)
            .background(
                if (filled)
                    MaterialTheme.colorScheme.tertiary
                    else
                        Color.Transparent)
            .border(2.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
    )
}