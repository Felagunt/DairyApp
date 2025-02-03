package com.example.diaryapp.navigation

import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

class MyNavActions(navController: NavHostController) {
    val navigateTo = { navBackStackEntry: NavBackStackEntry, route: String ->
        if (navBackStackEntry.lifecycleIsResumed()) {
            navController.navigate(route)
        }
    }
    val navigateUp = { navBackStackEntry: NavBackStackEntry ->
        if (navBackStackEntry.lifecycleIsResumed()) {
            navController.navigateUp()
        }
    }
    val popBackStackAndNavigate =
        { navBackStackEntry: NavBackStackEntry, route: String?, popUpTo: String, inclusive: Boolean ->
            if (navBackStackEntry.lifecycleIsResumed()) {
                navController.popBackStack(popUpTo, inclusive)
                route?.let {
                    navController.navigate(route)
                }
            }
        }
}
/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED