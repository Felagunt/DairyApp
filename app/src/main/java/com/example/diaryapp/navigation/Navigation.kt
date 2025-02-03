package com.example.diaryapp.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.diaryapp.diary_feature.presentation.add_edit_diary.AddEditDiaryScreen
import com.example.diaryapp.diary_feature.presentation.add_edit_diary.AddEditDiaryViewModel
import com.example.diaryapp.diary_feature.presentation.listOfDiary.DiaryViewModel
import com.example.diaryapp.diary_feature.presentation.listOfDiary.components.DiaryScreen
import com.example.diaryapp.lockScreen_feature.presentation.LockScreen
import com.example.diaryapp.navigation.MyNavActions

@SuppressLint("ComposableDestinationInComposeScope")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()


    val myNavActions = remember(navController) {
        MyNavActions(navController)
    }

    NavHost(
        navController = navController,
        startDestination = Screen.DiaryScreen.route
    ) {




//        if(navController.currentBackStackEntry?.lifecycleIsResumed()) {
//            navController.navigate(Screen.LockScreen.route)
//        }
        composable(
            route = Screen.DiaryScreen.route
        ) {navBackStack ->
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
////            )
//            DiaryScreen(
//                onNavigate = {
//                             myNavActions.navigateTo(navBackStack, it.route)
//                },
//                state =,
//                quoteState =,
//                onEvent =,
//                uiEvent =
//            )
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
            val viewModel = hiltViewModel<AddEditDiaryViewModel>()
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

//
//private fun NavBackStackEntry.lifecycleIsResumed() =
//    this.lifecycle.currentState = Lifecycle.State.RESUMED
//
//

@Composable
fun LockApp(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "LockScreen",
    ) {
        composable(route = "LockScreen") {

            LockScreen(
                toNavigate = {
                    navController.navigate("Home") {
                        popUpTo("LockScreen") {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(route = "Home") {
            //HomeScreen()
        }
    }
}