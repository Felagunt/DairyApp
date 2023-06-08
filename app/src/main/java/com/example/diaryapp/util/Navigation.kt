package com.example.diaryapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.diaryapp.diary_feature.presentation.Screen
import com.example.diaryapp.diary_feature.presentation.add_edit_diary.AddEditDiaryScreen
import com.example.diaryapp.diary_feature.presentation.add_edit_diary.AddEditDiaryViewModel
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryViewModel
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
            val viewModel = viewModel<DiaryViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val quoteState = viewModel.quoteState.value
            val uiEvent = viewModel.uiEvent
            DiaryScreen(
                state = state,
                quoteState = quoteState,
                onEvent = viewModel::onEvent,
                uiEvent = uiEvent,
                onNavigate = { navController.navigate(it.route) }
            )
        }
        composable(
            route = Screen.AddEditDiaryScreen.route + "?{diaryId}=diaryId",
            arguments = listOf(
                navArgument(
                    name = "diaryId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            val viewModel = viewModel<AddEditDiaryViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val uiEvent = viewModel.uiEvent
            AddEditDiaryScreen(
                state = state,
                onEvent = viewModel::onEvent,
                uiEvent = uiEvent,
                onPopBackStack = { navController.popBackStack() }
            )
        }
    }
}