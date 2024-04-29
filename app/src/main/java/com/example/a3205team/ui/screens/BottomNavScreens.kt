@file:Suppress("ktlint:standard:package-name")

package com.example.a3205team.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.a3205team.R
import com.example.history.screens.history.HistoryScreen
import com.example.search.screens.search.SearchScreen

sealed class BottomNavScreens(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val resourceId: Int,
) {
    object Search : BottomNavScreens("search_main", R.drawable.ic_search, R.string.search)

    object History : BottomNavScreens("history_main", R.drawable.ic_history, R.string.history)
}

fun NavGraphBuilder.bottomNavScreens(navController: NavHostController) {
    composable(BottomNavScreens.Search.route) { SearchScreen(navController) }
    composable(BottomNavScreens.History.route) { HistoryScreen(navController) }
}
