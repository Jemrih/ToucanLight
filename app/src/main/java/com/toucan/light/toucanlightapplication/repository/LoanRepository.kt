package com.toucan.light.toucanlightapplication.repository

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.toucan.light.toucanlightapplication.firebase.RxFirestore
import com.toucan.light.toucanlightapplication.storage.entity.Account
import com.toucan.light.toucanlightapplication.storage.entity.Loan
import com.toucan.light.toucanlightapplication.storage.persistance.dao.AccountDao
import com.toucan.light.toucanlightapplication.storage.persistance.dao.LoanDao
import com.toucan.light.toucanlightapplication.utils.extensions.getAccountsReference
import com.toucan.light.toucanlightapplication.utils.extensions.getLoansReference

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class LoanRepository(private val loanDao: LoanDao) {
    fun getLoans(): Observable<List<Loan>> {
        return Observable.concatArray(
            getLoansFromStorage(),
            getLoansFromApi())
    }

    private fun getLoansFromStorage(): Observable<List<Loan>> {
        return loanDao.findAllLoans().filter { it.isNotEmpty() }
            .toObservable()
    }

    private fun getLoansFromApi(): Observable<List<Loan>> {
        val fireStore = FirebaseFirestore.getInstance()
        val loansReference = fireStore.getLoansReference()
        return RxFirestore.getCollection(loansReference)
            .map { query ->
                val loans = mutableListOf<Loan>()
                query.documents.mapTo(loans) {
                    it.toObject(Loan::class.java)!!
                }
                return@map loans.toList()
            }
            .toObservable()
            .doOnNext {
                storeLoansInDb(it)
            }
    }

    private fun storeLoansInDb(loans: List<Loan>) {
        Observable.fromCallable { loanDao.insertLoans(loans) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("LoanRepository", "Inserted ${loans.size} loans from API in DB...")
            }
    }
}