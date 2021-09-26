package com.hritik.bilancio.ui.navigation

import com.hritik.bilancio.modal.TransactionEntity

sealed class Screen(val route:String){
    object  SplashScreen :Screen("splash_screen")
    object  HomeScreen :Screen("home_screen")
    object  AddTransaction: Screen("add_transaction_screen")
    object  DetailTransaction: Screen("detail_transaction_screen")
    object AllIncome: Screen("all_income_screen")
    object AllExpense: Screen("all_expense_screen")

    fun withTransactionArgs(vararg args : Int) : String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}
