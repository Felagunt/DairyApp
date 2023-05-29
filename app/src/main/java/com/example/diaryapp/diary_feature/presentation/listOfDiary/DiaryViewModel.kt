package com.example.diaryapp.diary_feature.presentation.listOfDiary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.diary_feature.domain.model.Diary
import com.example.diaryapp.diary_feature.domain.repository.DiaryRepository
import com.example.diaryapp.diary_feature.presentation.Screen
import com.example.diaryapp.diary_feature.presentation.add_edit_diary.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    var state by mutableStateOf(DiaryState(emptyList()))
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var recentlyDeletedDiary: Diary? = null

    init {
        getDiaries()
    }

    fun onEvent(event: DiariesEvent) {
        when(event) {
            is DiariesEvent.OnDiaryClick -> {
                //navigate
                sendUiEvent(
                    UiEvent.Navigate(
                    Screen.AddEditDiaryScreen.route + "?diaryId=${event.diary.diaryId}"
                )
                )
            }
            is DiariesEvent.OnDeleteDiaryClick -> {
                viewModelScope.launch {
                    diaryRepository.deleteDiary(event.diary)
                    sendUiEvent(UiEvent.ShowSnackbar(
                        message = "Alarm deleted",
                        action = "Undo"
                    ))
                    recentlyDeletedDiary = event.diary
                }

            }
            is DiariesEvent.OnRestoreDiariesClick -> {
                viewModelScope.launch {
                    diaryRepository.insertDiary(recentlyDeletedDiary ?: return@launch)
                    recentlyDeletedDiary = null
                }
            }
            is DiariesEvent.OnAddDiaryClick -> {
                sendUiEvent(UiEvent.Navigate(Screen.AddEditDiaryScreen.route))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private fun getDiaries() {
        viewModelScope.launch {
            state = state.copy(
                diaries = diaryRepository.getDiaries()
            )
        }
    }
}