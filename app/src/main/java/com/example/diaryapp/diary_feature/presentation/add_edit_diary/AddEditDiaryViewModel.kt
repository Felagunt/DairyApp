package com.example.diaryapp.diary_feature.presentation.add_edit_diary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.diary_feature.domain.model.Diary
import com.example.diaryapp.diary_feature.domain.repository.DiaryRepository
import com.example.diaryapp.diary_feature.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddEditDiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

//    var state by mutableStateOf(AddEditState())
//        private set

    private val _state = MutableStateFlow(AddEditState())
    val state = _state.asStateFlow()


    private val _eventFlow = Channel<UiEvent>()
    val uiEvent = _eventFlow.receiveAsFlow()

    private var currentDiaryId: Int? = null


    //val lifecycleOwner = LocalLifecycleOwner.current.lifecycleScope


    init {
        savedStateHandle.get<Int>("diaryId")?.let { diaryId ->
            if (diaryId != -1) {
                viewModelScope.launch {
                    diaryRepository.getDiaryById(diaryId)?.also { diary ->
                        currentDiaryId = diary.diaryId
                        _state.value = _state.value.copy(
                            content = diary.content,
                            title = diary.title,
                            timestamp = diary.timestamp,
                            diaryId = diary.diaryId
                        )
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditDiaryEvent) {
        when (event) {
            is AddEditDiaryEvent.OnLeavePage -> {
                //TODO refactor
            }

            is AddEditDiaryEvent.OnChangeContent -> {
//                state.value.content = state.value.diary?.copy(
//                    content = event.value
//                )
                _state.update {
                    it.copy(
                        content = event.value
                    )
                }
            }

            is AddEditDiaryEvent.OnChangeTitle -> {
//
//                    state.value.diary = state.value.diary?.copy(
//                        title = event.value
//                    )
                _state.update {
                    it.copy(
                        title = event.value
                    )
                }
            }

            is AddEditDiaryEvent.SaveDiary -> {
                val currentTime =
                    LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
                    )
                viewModelScope.launch {
                    if (state.value.content.isBlank()) {
                        sendEvent(UiEvent.ShowSnackbar("Type some content"))
                        return@launch
                    } else {
                        try {
                            diaryRepository.insertDiary(
                                Diary(
                                    title = state.value.title ?: "",
                                    content = state.value.content,
                                    timestamp = currentTime,
                                    diaryId = state.value.diaryId
                                )
                            )
                            sendEvent(UiEvent.PopBackStack)
                        } catch (e: Exception) {
                            sendEvent(
                                UiEvent.ShowSnackbar(
                                    message = e.message ?: "Error save"
                                )
                            )

                        }
                    }
                }
            }

            is AddEditDiaryEvent.OnDeleteDiaryClick -> {
                viewModelScope.launch {
                    val state = state.value
                    val diary = Diary(
                        diaryId = state.diaryId,
                        title = state.title,
                        content = state.content,
                        timestamp = state.timestamp
                    )
                    diaryRepository.deleteDiary(diary)
                }
            }
        }
    }

    private fun sendEvent(event: UiEvent) {
        viewModelScope.launch {
            _eventFlow.send(event)
        }
    }
}















