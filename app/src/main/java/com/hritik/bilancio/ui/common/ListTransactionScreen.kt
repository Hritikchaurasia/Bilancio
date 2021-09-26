package com.hritik.bilancio.ui.common

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hritik.bilancio.modal.TransactionEntity
import com.hritik.bilancio.ui.homescreen.Header

@ExperimentalMaterialApi
@Composable
fun ListTransactionScreen(
    navController: NavHostController,
    transactionList: List<TransactionEntity>,
    title: String
) {

    Scaffold(

        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        topBar = { Header(title) },

        content = {
            LazyColumn(content = {
                items(transactionList.size) { index ->
                    TransactionCard(
                        navController = navController,
                        transaction = transactionList[index]
                    )
                }
            })
        }
    )

}