package com.example.diaryapp.diary_feature.presentation.add_edit_diary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diaryapp.diary_feature.presentation.UiEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditDiaryScreen(
    onPopBackStack: () -> Unit,
    state: AddEditState,
    uiEvent: Flow<UiEvent>?, // maybe?
    onEvent: (AddEditDiaryEvent) -> Unit
) {

    val snackbarHostState = remember { SnackbarHostState() }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = true) {
        uiEvent?.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is UiEvent.PopBackStack -> {
                    //TODO navigation
                    onPopBackStack()
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
                    onEvent(AddEditDiaryEvent.SaveDiary)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save diary"
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My diary")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent(AddEditDiaryEvent.OnLeavePage) //TODO
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Go Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        onEvent(AddEditDiaryEvent.OnDeleteDiaryClick)
                    }) {
                        Icon(
                            imageVector = Icons.Default.DeleteOutline,
                            contentDescription = "Delete"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.title,
                onValueChange = {
                    onEvent(
                        AddEditDiaryEvent.OnChangeTitle(state.title)
                    )
                },
                placeholder = {
                    Text(text = "Enter title")
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = state.content,
                onValueChange = {
                    onEvent(
                        AddEditDiaryEvent.OnChangeContent(state.content)
                    )
                },
                placeholder = {
                    Text(text = "Type some today's diary")
                },
                textStyle = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddEditDiaryScreenPreview() {
    AddEditDiaryScreen(
        state = AddEditState(
            diaryId = null,
            title = "Tom Bumbodil",
            content = "Tom was here",
            timestamp = "2008-03-11 11:32"
        ),
        uiEvent = null,
        onEvent = {},
        onPopBackStack = {}
    )
}