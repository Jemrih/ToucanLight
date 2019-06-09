package com.toucan.light.toucanlightapplication.mvp.views.history.impl

sealed class HistoryListTypes {
    data class SmallHeader(val header: String) : HistoryListTypes()
    data class Header(val header: String, val description: String) : HistoryListTypes()
    data class Transaction(
        val transactionId: String,
        val header: String,
        val description: String,
        val amount: String
    ) :
        HistoryListTypes()
}