package com.example.assignment.route.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainBottomBarScreen(
    navController: NavHostController = rememberNavController(),
    mainNavController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController, paddingValues = it, mainNavController= mainNavController)
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val bottomBarScreen = listOf(
        BottomBarScreen.HomeScreen,
        BottomBarScreen.FavoriteScreen,
        BottomBarScreen.NotifycationScreen,
        BottomBarScreen.InfomationScreen
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    BottomNavigation(
        backgroundColor = Color.White
    ) {
        bottomBarScreen.forEach {
            BottomBarItem(navController = navController, screen = it, destination = currentDestination)
        }
    }
}


@Composable
fun RowScope.BottomBarItem(
    navController: NavHostController,
    screen: BottomBarScreen,
    destination: NavDestination?
) {

    val selected = destination?.hierarchy?.any {
        it.route == screen.route
    } == true
    BottomNavigationItem(
        selected = selected,
        onClick = {
               navController.navigate(route = screen.route)
        },
        icon = {
            Icon(
                painter = painterResource(
                    id = if (selected) {
                        screen.focusedIcon
                    } else {
                        screen.unFocusedIcon
                    }
                ),
                contentDescription = screen.title,
                modifier = Modifier.size(24.dp)
            )
        },
        alwaysShowLabel = false)


}