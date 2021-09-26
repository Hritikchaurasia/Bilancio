package com.hritik.bilancio.ui.homescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.hritik.bilancio.R
import com.hritik.bilancio.modal.TransactionEntity
import com.hritik.bilancio.ui.common.BaseScreen
import com.hritik.bilancio.ui.common.Loading
import com.hritik.bilancio.ui.common.TransactionCard
import com.hritik.bilancio.ui.navigation.Screen
import com.hritik.bilancio.utils.ViewState


@ExperimentalMaterialApi
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeScreenViewModel = viewModel()) {

    val currentUiState = viewModel.uiState.collectAsState()

    currentUiState.value.let { state ->
        when (state) {
            is ViewState.Loading -> {
                Loading()
            }
            is ViewState.Empty -> {
                BaseScreen(navController = navController ,displayText = stringResource(R.string.NODATA),title = "DASHBOARD")
            }
            is ViewState.Success -> {
                HomeScreenSuccessState(
                    navController = navController,
                    transactions = state.transactions,
                    totalBalance = state.totalBalance,
                    totalExpense = state.totalExpense,
                    totalIncome = state.totalIncome
                )
            }
            is ViewState.Error -> {
                BaseScreen(navController = navController , displayText = stringResource(R.string.ERROROCCUR),title = "DASHBOARD")
            }

        }
    }

}

@Composable
fun Header(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.surface)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier.padding(18.dp)
        )
    }
}



@ExperimentalMaterialApi
@Composable
fun HomeScreenSuccessState(
    navController: NavHostController,
    transactions: List<TransactionEntity>,
    totalBalance: Double,
    totalIncome: Double,
    totalExpense: Double
) {
    Scaffold(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        topBar = { Header(title = "DASHBOARD") },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = Screen.AddTransaction.route)

            }) {
                Image(
                    imageVector = Icons.Default.Add,
                    colorFilter = ColorFilter.tint(color = Color.White),
                    contentDescription = "Add"
                )
            }
        },
        content = {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = MaterialTheme.colors.background)
                    .verticalScroll(
                        rememberScrollState()
                    ),

                ) {


                TotalBalance(totalBalance = totalBalance)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IncomeCard(transactionAmount = totalIncome.toString(), type = "Income", navController = navController)
                    IncomeCard(transactionAmount = totalExpense.toString(), type = "expense" , navController = navController)
                }
                Text(
                    text = stringResource(R.string.RecentTransactions),
                    modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
                    color = MaterialTheme.colors.primaryVariant,
                )
                transactions.forEach { transaction ->
                    TransactionCard(navController = navController, transaction = transaction)
                }
            }
        }


    )

}

@Composable
fun TotalBalance(totalBalance: Double) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(vertical = 10.dp)
            .background(color = MaterialTheme.colors.surface),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.TOTALBALANCE),
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = totalBalance.toString(),
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.h1
            )
        }
    }
}

@Composable
fun IncomeCard(transactionAmount: String, type: String , navController: NavHostController) {
    val arrowColor: Color
    val arrowBackGroundColor: Color
    val arrow: ImageVector
    val incomeType: String
    val navigateScreen: String
    if (type == "Income") {
        incomeType = "TOTAL INCOME"
        arrowBackGroundColor = Color(0xFFC8E9D6)
        arrowColor = MaterialTheme.colors.onSecondary
        arrow = Icons.Default.KeyboardArrowUp
        navigateScreen = Screen.AllIncome.route
    } else {
        incomeType = "TOTAL EXPENSE"
        arrowBackGroundColor = Color(0xFFE4B6B6)
        arrowColor = MaterialTheme.colors.onError
        arrow = Icons.Default.KeyboardArrowDown
        navigateScreen = Screen.AllExpense.route
    }


  Button(onClick = {
      navController.navigate(route = navigateScreen )
  }) {
      Box(
          modifier = Modifier
              .padding(vertical = 3.dp)
              .height(150.dp)
              .width(180.dp)
              .background(color = MaterialTheme.colors.surface)
      ) {
          Row(
              modifier = Modifier
                  .fillMaxHeight()
                  .fillMaxWidth()
                  .padding(1.dp),
              horizontalArrangement = Arrangement.SpaceBetween,
          ) {
              Column(modifier = Modifier
                  .fillMaxHeight()
                  .padding(start = 3.dp),

                  verticalArrangement = Arrangement.Bottom,
                  horizontalAlignment = Alignment.Start, content = {
                      Text(
                          text = incomeType,
                          color = MaterialTheme.colors.primaryVariant,
                          style = MaterialTheme.typography.h5
                      )
                      Text(
                          text = transactionAmount,
                          color = MaterialTheme.colors.primaryVariant,
                          style = MaterialTheme.typography.h3,

                          )
                  })
              Box(
                  modifier = Modifier
                      .size(30.dp)
                      .clip(shape = CircleShape)
                      .background(color = arrowBackGroundColor)
              ) {
                  Image(
                      imageVector = arrow,
                      contentDescription = "arrow",
                      colorFilter = ColorFilter.tint(color = arrowColor),
                      modifier = Modifier.align(
                          Alignment.Center
                      )
                  )
              }
          }
      }
  }
}



