package com.example.diaryapp.diary_feature.presentation

sealed class Screen(val route: String) {

    object DiaryScreen: Screen("diary_screen")
    object AddEditDiaryScreen: Screen("add_edit_diary_screen")
}
