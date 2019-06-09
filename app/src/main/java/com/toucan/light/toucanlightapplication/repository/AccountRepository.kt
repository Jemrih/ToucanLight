package com.toucan.light.toucanlightapplication.repository

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.toucan.light.toucanlightapplication.firebase.RxFirestore
import com.toucan.light.toucanlightapplication.storage.entity.Account
import com.toucan.light.toucanlightapplication.storage.persistance.dao.AccountDao
import com.toucan.light.toucanlightapplication.utils.extensions.getAccountsReference

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class AccountRepository(private val accountDao: AccountDao) {
    fun getAccounts(): Observable<List<Account>> {
        return Observable.concatArray(
            getAccountsFromStorage(),
            getAccountsFromApi())
    }

    private fun getAccountsFromStorage(): Observable<List<Account>> {
        return accountDao.findAllAccounts().filter { it.isNotEmpty() }
            .toObservable()
    }

    private fun getAccountsFromApi(): Observable<List<Account>> {
        val fireStore = FirebaseFirestore.getInstance()
        val accountsReference = fireStore.getAccountsReference()
        return RxFirestore.getCollection(accountsReference)
            .map { query ->
                val accounts = mutableListOf<Account>()
                query.documents.mapTo(accounts) {
                    it.toObject(Account::class.java)!!
                }
                return@map accounts.toList()
            }
            .toObservable()
            .doOnNext {
                storeAccountsInDb(it)
            }
    }

    private fun storeAccountsInDb(accounts: List<Account>) {
        Observable.fromCallable { accountDao.insertAccounts(accounts) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("ChatRepository", "Inserted ${accounts.size} accounts from API in DB...")
            }
    }
}