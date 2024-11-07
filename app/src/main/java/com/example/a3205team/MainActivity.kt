package com.example.a3205team

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.a3205team.di.ConnectionManager
import com.example.a3205team.ui.screens.BottomNavScreens
import com.example.a3205team.ui.screens.bottomNavScreens
import com.example.a3205team.ui.screens.splash.SplashScreen
import com.example.a3205team.ui.theme.MainViewModel
import com.example.a3205team.ui.theme._3205TeamTheme
import com.example.presentation.base_ui.theme.AppTheme
import com.example.presentation.base_ui.theme.appDarkColors
import com.example.presentation.base_ui.theme.appLightColors
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var connectionManager: ConnectionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ProvideWindowInsets {
                this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
                val darkTheme: Boolean = isSystemInDarkTheme()
                val colors = if (darkTheme) appDarkColors() else appLightColors()
                val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }
                connectionManager.connectionLiveData.observe(this) {
                    if (!it) {
                        lifecycleScope.launch {
                            snackbarHostState.value.showSnackbar(
                                message = "Отсутствует интернет соединение",
                                duration = SnackbarDuration.Short,
                            )
                        }
                    }
                }
                AppTheme.AppTheme(colors = colors) {
                    SystemUi(windows = window)
                    Surface {
                        MainContent()

                        SnackbarHost(
                            hostState = snackbarHostState.value,
                        ) {
                            Snackbar(
                                modifier = Modifier.padding(12.dp),
                                backgroundColor = AppTheme.colors.colorBackgroundAlert,
                                snackbarData = it,
                                contentColor = AppTheme.colors.colorTextAlert,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _3205TeamTheme {
        Greeting("Android")
    }
}

@Composable
fun SystemUi(windows: Window) =
    AppTheme.AppTheme {
        windows.statusBarColor = AppTheme.colors.systemBackgroundPrimary.toArgb()
        windows.navigationBarColor = AppTheme.colors.systemBackgroundPrimary.toArgb()

        @Suppress("DEPRECATION")
        if (AppTheme.colors.systemBackgroundPrimary.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        @Suppress("DEPRECATION")
        if (AppTheme.colors.systemBackgroundPrimary.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }

val notShowBottomNav = listOf("splash_screen")

@Composable
fun BottomNav(navController: NavHostController) {
    val items =
        listOf(
            BottomNavScreens.Search,
            BottomNavScreens.History,
        )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    if (!notShowBottomNav.contains(currentDestination?.route)) {
        BottomNavigation(
            backgroundColor = AppTheme.colors.systemBackgroundSecondary,
            modifier =
                Modifier
                    .fillMaxWidth(),
            elevation = 0.dp,
        ) {
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = screen.iconId),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp),
                        )
                    },
                    label = {
                        Text(
                            stringResource(screen.resourceId),
                            color = AppTheme.colors.controlGraphBlueActive,
                            maxLines = 1,
                        )
                    },
                    selectedContentColor = AppTheme.colors.controlGraphBlueActive,
                    unselectedContentColor = AppTheme.colors.controlGraphBlueActive.copy(0.4f),
                    alwaysShowLabel = false,
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNav(navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash_screen",
            modifier = Modifier.padding(innerPadding),
        ) {
            composable("splash_screen") {
                SplashScreen(navController = navController)
            }
            bottomNavScreens(navController)
        }
    }
}
