package com.example.diaryapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.diary_feature.presentation.Screen
import com.example.diaryapp.diary_feature.presentation.add_edit_diary.AddEditDiaryScreen
import com.example.diaryapp.diary_feature.presentation.listOfDiary.components.DiaryScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.DiaryScreen.route
    ) {
        composable(
            route = Screen.DiaryScreen.route
        ) {
            DiaryScreen(navController)
        }
        composable(
            route = Screen.AddEditDiaryScreen.route + "/{diaryId}"
        ) {
            AddEditDiaryScreen()
        }
    }
}