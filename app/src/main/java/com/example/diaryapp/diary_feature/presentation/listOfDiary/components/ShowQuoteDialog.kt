package com.example.diaryapp.diary_feature.presentation.listOfDiary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiariesEvent
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryQuoteState
import com.example.diaryapp.quote_feature.domain.model.Quote

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

            usePlatformDefaultWidth = true
        )
    ) {
        Box(
            //elevation = 5.dp,

            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clip(shape = MaterialTheme.shapes.medium)
//                .background(MaterialTheme.colorScheme.surfaceContainer)
//                .border(
//                    1.dp,
//                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
//                    shape = RoundedCornerShape(15.dp)
//                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = quoteState.quote?.quote ?: "",
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = quoteState.quote?.author ?: "",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            if(quoteState.error.isNotBlank( )) {
                Text(
                    text = quoteState.error,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
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

@Preview(showBackground = true)
@Composable
fun previewShowDialog() {
    ShowQuoteDialog(quoteState = DiaryQuoteState(
        isLoading = false,
        error = "errro",
        quote = Quote(
            author = "wialiam",
            quote = "no one eternal"
        )
    ), onEvent = {})
}
