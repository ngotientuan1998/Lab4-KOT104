package com.duyle.lab4_kot104_md18306

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Login.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.Login.route) {
            LoginScreen(null, navController)
        }

        composable("${NavigationItem.Home.route}/{username}/{password}", arguments = listOf(
            navArgument("username") {
                type = NavType.StringType
                defaultValue = "user1234"
            },
            navArgument("password") {
                type = NavType.StringType
            }
        )) {backStackEntry ->
            val username = backStackEntry.arguments?.getString("username")
            val password = backStackEntry.arguments?.getString("password")
            Page2(nhanvienModel = NhanvienModel(username?:"", password?:""), navController)
        }
    }
}