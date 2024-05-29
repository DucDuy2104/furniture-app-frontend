package com.example.assignment.route.bottom_nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assignment.screen.FavoriteScreen
import com.example.assignment.screen.home_screen.HomeScreen
import com.example.assignment.screen.NotifycationScreen
import com.example.assignment.screen.ProfileScreen


@Composable
fun BottomNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    mainNavController: NavHostController
) {
    NavHost(navController = navController,
        startDestination = BottomBarScreen.HomeScreen.route) {
        composable(
            route = BottomBarScreen.HomeScreen.route
        ) {
            HomeScreen(paddingValues= paddingValues, navController = mainNavController)
        }

        composable(
            route = BottomBarScreen.FavoriteScreen.route
        ) {
            FavoriteScreen(paddingValues= paddingValues, navController = mainNavController)
        }

        composable(
            route = BottomBarScreen.NotifycationScreen.route
        ) {
            NotifycationScreen(paddingValues= paddingValues)
        }

        composable(
            route = BottomBarScreen.InfomationScreen.route
        ) {
            ProfileScreen(paddingValues= paddingValues, navController=mainNavController)
        }
    }
}