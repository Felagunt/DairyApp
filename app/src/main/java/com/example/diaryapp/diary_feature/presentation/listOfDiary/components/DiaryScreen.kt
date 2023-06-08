package com.example.diaryapp.diary_feature.presentation.listOfDiary.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.diaryapp.diary_feature.presentation.UiEvent
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiariesEvent
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryQuoteState
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DiaryScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    state: DiaryState,
    quoteState: DiaryQuoteState,
    onEvent: (DiariesEvent) -> Unit,
    uiEvent: Flow<UiEvent>//TODO Is  that good?
) {


//
//    val quoteState by remember {
//        mutableStateOf(viewModel.quoteState)
//    }//TODO

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        onEvent(DiariesEvent.OnRestoreDiariesClick)
                    }
                }
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(DiariesEvent.OnAddDiaryClick)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add diary"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Welcome to your diary",
                    style = MaterialTheme.typography.h4
                )
//                IconButton(onClick = {
//                    //TODO sorting
//                }
//
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Sort,
//                        contentDescription = "Sort"
//                    )
//                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.diaries) { diary ->
                    DiaryItem(
                        diary = diary,
                        onEvent = onEvent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onEvent(DiariesEvent.OnDiaryClick(diary))
                            }
                    )
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .background(MaterialTheme.colors.secondary)
                    )
                }
            }
        }
    }
    //if (quoteState.quote ) {

        ShowQuoteDialog(
            quoteState = quoteState,
            onEvent = onEvent
        )
    //}
}