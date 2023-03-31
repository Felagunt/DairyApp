package com.example.diaryapp.diary_feature.presentation.listOfDiary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.diary_feature.domain.model.Diary
import com.example.diaryapp.diary_feature.domain.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    var state by mutableStateOf(DiaryState(emptyList()))
        private set

    private val diaryEventChannel = Channel<DiariesEvent>()
    val diariesEvent = diaryEventChannel.receiveAsFlow()

    private var recentlyDeletedDiary: Diary? = null

    init {
        getDiaries()
    }

    fun onEvent(event: DiariesEvent) {
        when(event) {
            is DiariesEvent.OnDiaryClick -> {
                //navigate
            }
            is DiariesEvent.OnDeleteDiaryClick -> {
                viewModelScope.launch {
                    diaryRepository.deleteDiary(event.diary)
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
                //navigate
            }
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