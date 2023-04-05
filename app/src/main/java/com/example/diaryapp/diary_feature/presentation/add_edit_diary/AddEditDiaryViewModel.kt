package com.example.diaryapp.diary_feature.presentation.add_edit_diary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.diary_feature.domain.model.Diary
import com.example.diaryapp.diary_feature.domain.repository.DiaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddEditDiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(AddEditState())
        private set


    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentDiaryId: Int? = null

    init {
        savedStateHandle.get<Int>("diaryId")?.let { diaryId ->
            if (diaryId != -1) {
                viewModelScope.launch {
                    diaryRepository.getDiaryById(diaryId)?.also {diary ->
                        currentDiaryId = diary.diaryId
                        state = state.copy(
                            diary = diary
                        )
                    }
                }
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: AddEditDiaryEvent) {
        when (event) {
            is AddEditDiaryEvent.OnLeavePage -> {
                //TODO refactor
            }
            is AddEditDiaryEvent.OnChangeContent -> {
                state.diary = state.diary?.copy(
                    content = event.value
                )
            }
            is AddEditDiaryEvent.OnChangeTitle -> {
                state.diary = state.diary?.copy(
                    title = event.value
                )
            }
            is AddEditDiaryEvent.SaveDiary -> {
                val currentTime =
                    LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                    )
                viewModelScope.launch {
                    try {
                        diaryRepository.insertDiary(
                            Diary(
                                title = state.diary!!.title,
                                content = state.diary!!.content,
                                timestamp = currentTime,
                                diaryId = currentDiaryId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveDiary)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Error save"
                            )
                        )
                    }
                }
            }
        }
    }
}















