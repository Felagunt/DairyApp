package com.example.diaryapp.diary_feature.presentation.listOfDiary.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.diaryapp.diary_feature.presentation.UiEvent
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiariesEvent
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryQuoteState
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryState
import com.example.diaryapp.util.CollectFlowWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    state: DiaryState,
    quoteState: DiaryQuoteState,
    onEvent: (DiariesEvent) -> Unit,
    uiEvent: Flow<UiEvent?>//TODO Is  that good? No
) {

//    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarHostState by rememberUpdatedState(SnackbarHostState())

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val scope = rememberCoroutineScope()



//    CollectFlowWithLifecycle(flow = uiEvent) { event ->
//        when (event) {
//            is UiEvent.Navigate -> {
//                onNavigate(event)
//            }
//
//            is UiEvent.PopBackStack -> {
//            }
//
//            is UiEvent.ShowSnackbar -> {
//                scope.launch {
//                    val result = snackbarHostState.showSnackbar(
//                        message = event.message,
//                        actionLabel = event.action
//                    )
//                    if (result == SnackbarResult.ActionPerformed) {
//                        onEvent(DiariesEvent.OnRestoreDiariesClick)
//                    }
//                }
//            }
//
//            else -> Unit
//        }
//    }

    LaunchedEffect(key1 = true) {
        uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = snackbarHostState.showSnackbar(
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
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(DiariesEvent.OnAddDiaryClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add diary",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Diary")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                ),
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
            //.padding(padding)
            ,
            contentPadding = PaddingValues(
                top = 15.dp + padding.calculateTopPadding()
            )
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
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                )
            }
        }
    }
    quoteState.quote?.let {
        ShowQuoteDialog(
            quoteState = quoteState,
            onEvent = onEvent
        )

    }
}
//
//
//@Preview
//@Composable
//fun diaryItemPreview() {
//    DiaryItem(diary = Diary(
//        title = "Hello",
//        content = "some text",
//        timestamp = "2011.11.4"
//    ), onEvent = {})
//}