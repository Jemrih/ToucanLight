package com.toucan.light.toucanlightapplication.mvp.views.history.impl

import com.toucan.light.toucanlightapplication.repository.TransactionHistoryRepository
import com.toucan.light.toucanlightapplication.storage.entity.TransactionHistory
import io.reactivex.Observable

class HistoryFragmentModel(private val repository: TransactionHistoryRepository) {
    fun getTransactions(): Observable<List<TransactionHistory>> {
        return repository.getTransactionHistories()
    }
}