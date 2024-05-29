package com.example.assignment.route.main_nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.assignment.route.auth_nav.AuthNavGraph
import com.example.assignment.route.bottom_nav.MainBottomBarScreen
import com.example.assignment.route.constants.AUTHENTICATION_ROUTE
import com.example.assignment.route.constants.ORDERID_KEY
import com.example.assignment.route.constants.PRODUCTID_KEY
import com.example.assignment.screen.cart_screen.CartScreen
import com.example.assignment.screen.detail_screen.DetailScreen
import com.example.assignment.screen.order_screen.OrderScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = AUTHENTICATION_ROUTE) {
        AuthNavGraph(navController = navController)
        composable(
            route = GrapScreen.MainBottomBarScreen.route
        ) {
            MainBottomBarScreen(mainNavController = navController)
        }

        composable(
            route = GrapScreen.DetailScreen.route,
            arguments = listOf(
                navArgument(name = PRODUCTID_KEY) {
                    type = NavType.IntType
                }
            )
        ) {
            val productId = remember(it) { it.arguments?.getInt(PRODUCTID_KEY) }
            Log.d("Duy", "MainNavGraph: $productId")
            DetailScreen(navController, productId = productId)
        }

        composable(
            route = GrapScreen.CartScreen.route
        ) {
            CartScreen(navController)
        }

        composable(
            route = GrapScreen.OrderScreen.route
        ) {
            OrderScreen()
        }

    }
}


sealed class GrapScreen(val route: String) {

    data object MainBottomBarScreen : GrapScreen("bottom_screen")
    data object DetailScreen : GrapScreen("detail_screen/{${PRODUCTID_KEY}}") {
        fun passProductId(productId: Int): String {
            return "detail_screen/${productId}"
        }
    }

    data object CartScreen : GrapScreen("cart_screen")

    data object OrderScreen : GrapScreen("order_screen")

    data object OrderDetailScreen : GrapScreen("order_detail_screen/{$ORDERID_KEY}") {
        fun passOrderId(orderId: Int): String {
            return "order_detail_screen/$orderId"
        }
    }
}