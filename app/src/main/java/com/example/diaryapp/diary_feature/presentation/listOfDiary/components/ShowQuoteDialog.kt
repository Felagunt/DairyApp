package com.example.diaryapp.diary_feature.presentation.listOfDiary.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.diaryapp.quote_feature.domain.model.Quote

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowQuoteDialog(
    quote: Quote,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = {
                           onDismiss()
        },
        properties = DialogProperties(

            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .border(
                    1.dp,
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = quote.quote,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = quote.author,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}