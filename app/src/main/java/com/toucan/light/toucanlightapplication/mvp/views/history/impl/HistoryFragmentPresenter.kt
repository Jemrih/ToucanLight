package com.toucan.light.toucanlightapplication.mvp.views.history.impl

import com.toucan.light.toucanlightapplication.utils.extensions.format
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.Date

class HistoryFragmentPresenter(
    private val view: HistoryFragmentView,
    private val model: HistoryFragmentModel,
    private val interactor: HistoryFragmentInteractor
) {
    //region Members
    private var transactionDisposable: Disposable? = null
    private lateinit var upcomingTransactionsTitle: String
    private lateinit var upcomingTransactionsDescription: String
    //endregion

    //region Presenter Lifecycle
    fun initialize(
        upcomingTransactionsTitle: String,
        upcomingTransactionsDescription: String
    ) {
        this.upcomingTransactionsTitle = upcomingTransactionsTitle
        this.upcomingTransactionsDescription = upcomingTransactionsDescription
    }

    fun onResume() {
        getTransactionData()
    }

    fun onPause() {
        transactionDisposable?.dispose()
    }
    //endregion

    private fun getTransactionData() {
        var current: Date?
        transactionDisposable = model.getTransactions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                current = null
                val result = mutableListOf<HistoryListTypes>()

                result.add(
                    HistoryListTypes.Header(
                        upcomingTransactionsTitle,
                        upcomingTransactionsDescription
                    )
                )
                //Start by sorting list incase server gives us a scrambled list
                it.sortedByDescending { item -> item.transactionDate.time }.forEach { transaction ->
                    //This is not safe if time is different (But for this demo we count all time as same)
                    if (current == null || transaction.transactionDate.before(current)) {
                        current = transaction.transactionDate
                        result.add(HistoryListTypes.SmallHeader(transaction.transactionDate.format("MM/dd/yyyy")!!))
                    }
                    result.add(
                        HistoryListTypes.Transaction(
                            transaction.transactionId,
                            transaction.title,
                            transaction.description,
                            transaction.amount
                        )
                    )
                }

                return@map result
            }
            .subscribe({ list ->
                view.submitTransactionHistory(list)
            }, {
                view.showErrorNotice()
            })
    }

    fun onListItemClicked(): (HistoryListTypes) -> Unit = {

    }
}