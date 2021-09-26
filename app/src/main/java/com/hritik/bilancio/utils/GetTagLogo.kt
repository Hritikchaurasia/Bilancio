package com.hritik.bilancio.utils


import com.hritik.bilancio.R

fun getTransactionTypeLogo(tag: String): Int {

    when (tag) {
        "Housing" -> return R.drawable.ic_housing
        "Entertainment" -> return R.drawable.ic_entertainment
        "Food" -> return R.drawable.ic_food
        "Insurance" -> return R.drawable.ic_insurance
        "Medical" -> return R.drawable.ic_medical
        "Others" -> return R.drawable.ic_others
        "Personal" -> return R.drawable.ic_personal_spending
        "Saving" -> return R.drawable.ic_saving
        "Transport" -> return R.drawable.ic_transport
        "Utilities" -> return R.drawable.ic_utilities
        else -> return R.drawable.ic_others

    }

}