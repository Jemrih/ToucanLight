package com.toucan.light.toucanlightapplication.mvp.views.history.impl

interface HistoryFragmentView {
    fun showErrorNotice()
    fun submitTransactionHistory(list: List<HistoryListTypes>)
}