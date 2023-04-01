package com.example.diaryapp.diary_feature.presentation.listOfDiary.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiariesEvent
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //TODO navigate
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
                .padding(16.dp)
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
                IconButton(onClick = {
                    //TODO navigate
                }

                ) {
                    Icon(
                        imageVector = Icons.Default.Sort,
                        contentDescription = "Sort"
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(state.diaries) {diary ->
                    DiaryItem(
                        diary = diary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //TODO navigation
                            },
                        onDeleteDiaryClick = {
                            viewModel.onEvent(DiariesEvent.OnDeleteDiaryClick(diary))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Note deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(DiariesEvent.OnRestoreDiariesClick)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier
                        .height(16.dp)
                        .background(MaterialTheme.colors.secondary)
                    )
                }
            }
        }
    }
}