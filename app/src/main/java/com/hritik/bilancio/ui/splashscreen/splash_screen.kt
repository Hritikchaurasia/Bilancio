package com.hritik.bilancio.ui.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.hritik.bilancio.R
import com.hritik.bilancio.ui.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val KEY1 = stringResource(R.string.SPLASH_SCREEN_KEY1)

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Image(painter = painterResource(id = R.drawable.ic_bilancio_logo),

                contentDescription = "bilancio logo", modifier = Modifier
                    .align(
                        Alignment.Center)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(0.5f))
        }


    }

    LaunchedEffect(KEY1) {
        delay(2000L)
        navController.navigate(route = Screen.HomeScreen.route) {
            popUpTo(route = "splash_screen") { inclusive = true }
        }
    }

}