package com.example.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.assignment.route.main_nav.MainNavGraph
import com.example.assignment.ui.theme.AssignmentTheme

class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentTheme {
                navController = rememberNavController()
                MainNavGraph(navController = navController)
            }
        }
    }
}

