package com.example.assignment.route.auth_nav

import android.annotation.SuppressLint
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.assignment.route.constants.AUTHENTICATION_ROUTE
import com.example.assignment.screen.auth_screen.LoginScreen
import com.example.assignment.screen.auth_screen.SignUpScreen
import com.example.assignment.screen.SplashScreen

@SuppressLint("ComposableNavGraphInComposeScope")
fun NavGraphBuilder.AuthNavGraph(
    navController: NavHostController
) {

    navigation(
        startDestination = AuthScreen.SplashAuthScreen.route,
        route = AUTHENTICATION_ROUTE
    ) {
        composable(
            route = AuthScreen.SplashAuthScreen.route
        ) {
            SplashScreen(navController)
        }

        composable(
            route = AuthScreen.SignUpAuthScreen.route
        ) {
            SignUpScreen(navController)
        }

        composable(
            route = AuthScreen.LoginAuthScreen.route
        ) {
            LoginScreen(navController)
        }
    }

}