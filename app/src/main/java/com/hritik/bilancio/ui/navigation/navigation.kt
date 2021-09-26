package com.hritik.bilancio.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.NavType.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.hritik.bilancio.modal.TransactionEntity
import com.hritik.bilancio.ui.addtransaction.AddTransactionScreen
import com.hritik.bilancio.ui.addtransaction.AddtransactionViewModel
import com.hritik.bilancio.ui.allexpensescreen.AllexpenseScreen
import com.hritik.bilancio.ui.allexpensescreen.AllexpenseViewModel
import com.hritik.bilancio.ui.allincomescreen.AllincomeScreen
import com.hritik.bilancio.ui.allincomescreen.AllincomeViewModel
import com.hritik.bilancio.ui.detailtransaction.DetailtransactionScreen
import com.hritik.bilancio.ui.detailtransaction.DetailtransactionViewModel
import com.hritik.bilancio.ui.homescreen.HomeScreen
import com.hritik.bilancio.ui.homescreen.HomeScreenViewModel
import com.hritik.bilancio.ui.splashscreen.SplashScreen


@ExperimentalMaterialApi
@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {

            SplashScreen(navController = navController)
        }

        composable(route = Screen.HomeScreen.route) {
            val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(navController = navController, viewModel = homeScreenViewModel)
        }

        composable(route = Screen.AddTransaction.route) {
            val addtransactionViewModel = hiltViewModel<AddtransactionViewModel>()
            AddTransactionScreen(navController = navController, viewModel = addtransactionViewModel)

        }
        composable(route = Screen.DetailTransaction.route + "/{transactionID}",
            arguments = listOf(
                navArgument("transactionID") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { entry ->
            val detailtransactionViewModel = hiltViewModel<DetailtransactionViewModel>()
            DetailtransactionScreen(
                navController = navController,
                transactionId = entry.arguments?.getInt("transactionID")!!,
                viewModel = detailtransactionViewModel
            )

        }

        composable(route = Screen.AllIncome.route ){
            val allincomeViewModel = hiltViewModel<AllincomeViewModel>()
            AllincomeScreen(navController = navController, viewModel = allincomeViewModel)
        }
        composable(route = Screen.AllExpense.route ){
            val allexpenseViewModel = hiltViewModel<AllexpenseViewModel>()
            AllexpenseScreen(navController = navController, viewModel = allexpenseViewModel)
        }

    }

}