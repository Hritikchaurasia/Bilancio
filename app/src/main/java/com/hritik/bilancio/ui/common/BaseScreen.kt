package com.hritik.bilancio.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hritik.bilancio.ui.homescreen.Header
import com.hritik.bilancio.ui.navigation.Screen


@Composable
fun BaseScreen(navController: NavHostController, displayText : String , title: String) {

    Scaffold(

        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = { Header(title) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = MaterialTheme.colors.background),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = displayText , style = MaterialTheme.typography.h3, color = MaterialTheme.colors.primaryVariant,textAlign = TextAlign.Center)

                TextButton(
                    onClick = { navController.navigate(route = Screen.AddTransaction.route) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    ),
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text("Add Transaction", color = Color.White)
                }

            }
        }
    )


}