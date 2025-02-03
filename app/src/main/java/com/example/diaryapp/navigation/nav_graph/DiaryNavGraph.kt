package com.example.diaryapp.navigation.nav_graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.diaryapp.diary_feature.presentation.add_edit_diary.AddEditDiaryScreen
import com.example.diaryapp.diary_feature.presentation.add_edit_diary.AddEditDiaryViewModel
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryViewModel
import com.example.diaryapp.diary_feature.presentation.listOfDiary.components.DiaryScreen
import com.example.diaryapp.navigation.DIARY_ROUTE
import com.example.diaryapp.navigation.Screen

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.diaryNavGraph(navController: NavHostController) {

    navigation(
        startDestination = Screen.DiaryScreen.route,
        route = DIARY_ROUTE
    ) {
        composable(
            route = Screen.DiaryScreen.route
        ) {
            val viewModel = hiltViewModel<DiaryViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            val quoteState = viewModel.quoteState.value
            val uiEvent = viewModel.uiEvent

//            DiaryScreen(
//                state = state,
//                quoteState = quoteState,
//                onEvent = viewModel::onEvent,
//                uiEvent = uiEvent,
//                onNavigate = { navController.navigate(it.route) }
//            )
            DiaryScreen(
                onNavigate = {
                    //myNavActions.navigateTo(navBackStack, it.route)
                    navController.navigate(it.route)
                },
                state = state,
                quoteState = quoteState,
                onEvent = viewModel::onEvent,
                uiEvent = uiEvent
            )
        }
//        composable(
//            route = Screen.AddEditDiaryScreen.route + "?{diaryId}=diaryId",
//            arguments = listOf(
//                navArgument(
//                    name = "diaryId"
//                ) {
//                    type = NavType.IntType
//
//                    //defaultValue = -1
//                }
//            )
//        ) {navBackStack ->
//            val diaryId = navBackStack.arguments?.getInt("diaryId")
//            val viewModel = hiltViewModel<AddEditDiaryViewModel>()
//            val state by viewModel.state.collectAsStateWithLifecycle()
//            val uiEvent = viewModel.uiEvent
//            AddEditDiaryScreen(
//                state = state,
//                onEvent = viewModel::onEvent,
//                uiEvent = uiEvent,
//                onPopBackStack = { navController.popBackStack() }
//            )
//        }

    }
}