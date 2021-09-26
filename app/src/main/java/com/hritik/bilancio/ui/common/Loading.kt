package com.hritik.bilancio.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Loading() {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )

    {

               CircularProgressIndicator(
       color =  MaterialTheme.colors.primaryVariant
    )


    }
}
