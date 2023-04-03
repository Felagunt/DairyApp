package com.example.diaryapp.diary_feature.presentation.add_edit_diary

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
    object SaveDiary: UiEvent()
}
