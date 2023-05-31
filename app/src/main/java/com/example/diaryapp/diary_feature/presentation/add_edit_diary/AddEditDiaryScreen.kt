package com.example.diaryapp.diary_feature.presentation.add_edit_diary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diaryapp.diary_feature.presentation.UiEvent
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddEditDiaryScreen(
    onPopBackStack: () -> Unit,
    diaryViewModel: AddEditDiaryViewModel = hiltViewModel()
) {

    val state = diaryViewModel.state

    val scaffoldState = rememberScaffoldState()


    LaunchedEffect(key1 = true) {
        diaryViewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    diaryViewModel.onEvent(AddEditDiaryEvent.SaveDiary)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save diary"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.onSurface)
                .padding(paddingValues = it)
        ) {
            /*Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier
                    .size(50.dp)
                    .shadow(15.dp, CircleShape)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.onSecondary)
                    .border(
                        width = 3.dp,
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = CircleShape
                    )
                )
            }*/
            Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.diary?.title ?: "",
                    onValueChange = {
                        diaryViewModel.onEvent(
                            AddEditDiaryEvent.OnChangeTitle(state.diary?.title ?: "")
                        )
                    },
                    placeholder = {
                        Text(text = "Enter title")
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.h5
                )
            Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.diary?.content ?: "",
                    onValueChange = {
                        diaryViewModel.onEvent(
                            AddEditDiaryEvent.OnChangeContent(state.diary!!.content)
                        )
                    },
                    placeholder = {
                        Text(text = "Type some today's diary")
                    },
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxHeight()
                )
        }
    }
}