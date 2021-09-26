package com.hritik.bilancio.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hritik.bilancio.modal.TransactionEntity
import com.hritik.bilancio.ui.navigation.Screen
import com.hritik.bilancio.ui.theme.background
import com.hritik.bilancio.utils.getTransactionTypeLogo

@ExperimentalMaterialApi
@Composable
fun TransactionCard(navController: NavHostController, transaction: TransactionEntity) {
    val transactionAmountColor: Color
    if (transaction.transactionType == "Income") {
        transactionAmountColor = MaterialTheme.colors.onSecondary
    } else {
        transactionAmountColor = MaterialTheme.colors.onError
    }
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .height(100.dp)
            .background(color = MaterialTheme.colors.surface),
        onClick = {
            navController.navigate(
                route = Screen.DetailTransaction.withTransactionArgs(transaction.id)
            )
        }
    ) {
        Box(modifier = Modifier.background(color = MaterialTheme.colors.surface)) {

            Row() {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(100.dp)
                        .background(color = if (isSystemInDarkTheme()) Color(0xFF747373) else Color(
                            0xFFC5C4C4))
                ) {
                    Image(painter = painterResource(id = getTransactionTypeLogo(transaction.tag)),
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primaryVariant),
                        contentDescription = "transaction logo", modifier = Modifier
                            .align(
                                Alignment.Center)
                            .fillMaxHeight(0.3f)
                            .fillMaxWidth(0.3f))

                }
                Row(
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Top) {
                        Text(text = transaction.title,
                            style = MaterialTheme.typography.h3,
                            color = MaterialTheme.colors.primaryVariant)
                        Text(text = transaction.tag, color = MaterialTheme.colors.primaryVariant , modifier = Modifier.padding(top = 10.dp))
                    }
                    Column(modifier = Modifier.fillMaxHeight().padding(vertical = 10.dp),
                        verticalArrangement = Arrangement.SpaceBetween) {
                        Text(text = transaction.amount.toString(), color = transactionAmountColor)
                        Text(text = "On " + transaction.date,
                            color = MaterialTheme.colors.primaryVariant)
                    }
                }
            }
        }
    }
}

