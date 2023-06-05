package com.example.diaryapp.diary_feature.presentation.listOfDiary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiariesEvent
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryQuoteState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowQuoteDialog(
    quoteState: DiaryQuoteState,
    onEvent: (DiariesEvent) -> Unit
) {

    Dialog(
        onDismissRequest = {
                           onEvent(DiariesEvent.OnDismissShownDialog)
        },
        properties = DialogProperties(

            usePlatformDefaultWidth = false
        )
    ) {
        Box(
            //elevation = 5.dp,

            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clip(shape = RoundedCornerShape(15.dp))
                .background(MaterialTheme.colors.onSurface)
                .border(
                    1.dp,
                    color = MaterialTheme.colors.onSecondary,
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
                    text = quoteState.quote!!.quote,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = quoteState.quote.author ,
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            if(quoteState.error.isNotBlank()) {
                Text(
                    text = quoteState.error,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(horizontal = 20.dp)
                )
            }
            if(quoteState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

    }
}