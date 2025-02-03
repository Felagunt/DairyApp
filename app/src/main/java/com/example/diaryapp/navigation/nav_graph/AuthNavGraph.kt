package com.example.diaryapp.navigation.nav_graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.diaryapp.lockScreen_feature.presentation.LockScreen
import com.example.diaryapp.navigation.AUTHENTICATION_ROUTE
import com.example.diaryapp.navigation.DIARY_ROUTE
import com.example.diaryapp.navigation.Screen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.LockScreen.route,
        route = AUTHENTICATION_ROUTE
    ) {
        composable(route = Screen.LockScreen.route) {
            LockScreen(
                toNavigate = {
                    navController.navigate(
                        DIARY_ROUTE
                    )
//                    {//TODO diary or add_edit
//                        popUpTo(Screen.LockScreen.route) {
//                            inclusive = true
//                        }
//                    }
                }
            )
        }
    }
}