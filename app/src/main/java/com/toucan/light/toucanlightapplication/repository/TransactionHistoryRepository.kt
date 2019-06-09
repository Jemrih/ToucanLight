package com.toucan.light.toucanlightapplication.repository

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.toucan.light.toucanlightapplication.firebase.RxFirestore
import com.toucan.light.toucanlightapplication.storage.entity.Account
import com.toucan.light.toucanlightapplication.storage.entity.Loan
import com.toucan.light.toucanlightapplication.storage.entity.TransactionHistory
import com.toucan.light.toucanlightapplication.storage.persistance.dao.AccountDao
import com.toucan.light.toucanlightapplication.storage.persistance.dao.LoanDao
import com.toucan.light.toucanlightapplication.storage.persistance.dao.TransactionHistoryDao
import com.toucan.light.toucanlightapplication.utils.extensions.getAccountsReference
import com.toucan.light.toucanlightapplication.utils.extensions.getLoansReference
import com.toucan.light.toucanlightapplication.utils.extensions.getTransactionHistoryReference

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class TransactionHistoryRepository(private val transactionHistoryDao: TransactionHistoryDao) {
    fun getTransactionHistories(): Observable<List<TransactionHistory>> {
        return Observable.concatArray(
            getTransactionHistoriesFromStorage(),
            getTransactionHistoriesFromApi()
        )
    }

    private fun getTransactionHistoriesFromStorage(): Observable<List<TransactionHistory>> {
        return transactionHistoryDao.findAllTransactionHistories().filter { it.isNotEmpty() }
            .toObservable()
    }

    private fun getTransactionHistoriesFromApi(): Observable<List<TransactionHistory>> {
        val fireStore = FirebaseFirestore.getInstance()
        val transactionsReference = fireStore.getTransactionHistoryReference()
        return RxFirestore.getCollection(transactionsReference)
            .map { query ->
                val transactions = mutableListOf<TransactionHistory>()
                query.documents.mapTo(transactions) {
                    it.toObject(TransactionHistory::class.java)!!
                }
                return@map transactions.toList()
            }
            .toObservable()
            .doOnNext {
                storeTransactionHistoriesInDb(it)
            }
    }

    private fun storeTransactionHistoriesInDb(transactionHistories: List<TransactionHistory>) {
        Observable.fromCallable {
            transactionHistoryDao.insertTransactionHistories(
                transactionHistories
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d(
                    "TransactionHistoryRepo",
                    "Inserted ${transactionHistories.size} transactions from API in DB..."
                )
            }
    }
}