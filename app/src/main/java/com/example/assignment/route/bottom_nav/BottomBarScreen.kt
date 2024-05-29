package com.example.assignment.route.bottom_nav

import com.example.assignment.R

sealed class BottomBarScreen(
    val title: String,
    val focusedIcon: Int,
    val unFocusedIcon: Int,
    val route: String
) {
    object HomeScreen: BottomBarScreen(
        route = "home",
        title = "home",
        focusedIcon = R.drawable.home_ch,
        unFocusedIcon = R.drawable.home
    )
    object FavoriteScreen: BottomBarScreen(
        route = "favorite",
        title = "favorite",
        focusedIcon = R.drawable.favor_ch,
        unFocusedIcon = R.drawable.favor
    )
    object NotifycationScreen : BottomBarScreen(
        route = "notifycation",
        title = "notifycation",
        focusedIcon = R.drawable.notify_ch,
        unFocusedIcon = R.drawable.notify
    )

    object InfomationScreen: BottomBarScreen(
        route = "info",
        title = "info",
        focusedIcon = R.drawable.info_ch,
        unFocusedIcon = R.drawable.info
    )
}