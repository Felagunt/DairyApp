package com.example.diaryapp.navigation


const val AUTHENTICATION_ROUTE = "authentication"
const val DIARY_ROUTE = "diary"
const val ROOT_ROUTE = "root"

sealed class Screen(val route: String) {

    object DiaryScreen: Screen("diary_screen")
    object AddEditDiaryScreen: Screen("add_edit_diary_screen")

    object LockScreen: Screen("lock_screen")
}
