package com.example.assignment.route.auth_nav



sealed class AuthScreen(val route: String) {
    object SplashAuthScreen: AuthScreen("splash_screens")
    object SignUpAuthScreen: AuthScreen("signup_screen")
    object LoginAuthScreen: AuthScreen("login_screen")
}