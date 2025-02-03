package com.example.diaryapp.navigation.nav_graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.navigation.AUTHENTICATION_ROUTE
import com.example.diaryapp.navigation.DIARY_ROUTE
import com.example.diaryapp.navigation.MyNavActions
import com.example.diaryapp.navigation.ROOT_ROUTE
import com.example.diaryapp.navigation.Screen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    //val navController = rememberNavController()

    val myNavActions = remember(navController) {//TODO nice things
        MyNavActions(navController)
    }

    NavHost(
        navController = navController,
        startDestination = DIARY_ROUTE,
        route = ROOT_ROUTE,
        modifier = modifier
    ) {
//        val nav = navController.currentBackStack.value
//        val back = navController.currentBackStackEntry?.lifecycle?.currentState
//        val lc: LifecycleOwner = LocalLifecycleOwner.current
//        navController.navigate(AUTHENTICATION_ROUTE)
//        val ns = if (back == Lifecycle.Event.ON_RESUME)
//        val backStack by navController.currentBackStackEntryAsState()
//        val currentRoute = backStack?.destination?.route
        diaryNavGraph(navController)
        authNavGraph(navController)
    }
}

//
//private fun NavBackStackEntry.lifecycleIsResumed() =
//    this.lifecycle.currentState = Lifecycle.State.RESUMED
//