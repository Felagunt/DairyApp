package com.example.diaryapp.diary_feature.presentation.listOfDiary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.diary_feature.domain.model.Diary
import com.example.diaryapp.diary_feature.domain.repository.DiaryRepository
import com.example.diaryapp.diary_feature.presentation.Screen
import com.example.diaryapp.diary_feature.presentation.UiEvent
import com.example.diaryapp.quote_feature.domain.use_case.GetQuoteUseCase
import com.example.diaryapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository,
    private val getQuoteUseCase: GetQuoteUseCase
) : ViewModel() {

    private val _state =  mutableStateOf(DiaryState())
        val state: State<DiaryState> = _state

    private val _quoteState =  mutableStateOf(DiaryQuoteState())
    val quoteState: State<DiaryQuoteState> = _quoteState


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var recentlyDeletedDiary: Diary? = null

    var isQuoteDialogShown by mutableStateOf(false)
    private set

    init {
        getDiaries()
        showQuotePerDay()
        //isQuoteDialogShown = true
    }

    fun onEvent(event: DiariesEvent) {
        when(event) {
            is DiariesEvent.OnDismissShownDialog -> {
                isQuoteDialogShown = false
            }
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
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
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

    private fun showQuotePerDay() {
        viewModelScope.launch {
            val lastItem = state.value.diaries.lastOrNull()
            val lastTime = LocalDate.parse(lastItem?.timestamp)
            val today = LocalDate.now()
            val daysBtw = Period.between(today, lastTime).days

            if(daysBtw >= 1) {
                isQuoteDialogShown = true
                getQuote()
            } else {
                return@launch
            }
        }
    }

    private fun getQuote() {
        getQuoteUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _quoteState.value = _quoteState.value.copy(
                        quote = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _quoteState.value = _quoteState.value.copy(
                        error = result.message ?: "An known error"
                    )
                }
                is Resource.Loading -> {
                    _quoteState.value = _quoteState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDiaries() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                diaries = diaryRepository.getDiaries()
            )
        }
    }
}