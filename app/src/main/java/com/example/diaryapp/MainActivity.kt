package com.example.diaryapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.diaryapp.ui.theme.DiaryAppTheme
import com.example.diaryapp.navigation.Navigation
import com.example.diaryapp.navigation.nav_graph.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiaryAppTheme {
//                val navController = rememberNavController()
//                Navigation()
//                NavGraph(navController = navController)
                MainApp()
            }
        }
    }

    @Composable
    private fun MainApp(
        modifier: Modifier = Modifier
    ) {
        val navController = rememberNavController()
        Scaffold { paddingValues ->
            NavGraph(
                navController = navController,
                modifier = modifier.padding(paddingValues)
            )
        }
    }
    override fun onResume() {
        super.onResume()
        //TODO show lock screen
    }

    override fun onPause() {
        super.onPause()

        //TODO here aswell lock screen
    }
}
