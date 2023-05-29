package com.example.diaryapp.diary_feature.presentation.add_edit_diary

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackbar(val message: String, val action: String? = null): UiEvent()
}
