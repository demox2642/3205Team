package com.example.a3205team.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.a3205team.R
import com.example.presentation.base_ui.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale =
        remember {
            androidx.compose.animation.core.Animatable(0f)
        }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec =
                tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    },
                ),
        )
        delay(3000L)
        navController.navigate("search_main")
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(AppTheme.colors.systemBackgroundPrimary),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_3205),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value),
        )
    }
}
