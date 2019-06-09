package com.toucan.light.toucanlightapplication.mvp.views.accounts.impl

import com.toucan.light.toucanlightapplication.storage.entity.Account
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AccountsFragmentPresenter(
    private val view: AccountsFragmentView,
    private val model: AccountsFragmentModel,
    private val interactor: AccountsFragmentInteractor
) {
    //region Members
    var accountsDisposable: Disposable? = null
    //endregion

    //region Presenter Lifecycle
    fun initialize() {

    }

    fun onResume() {
        setupAccounts()
    }

    fun onPause() {
        accountsDisposable?.dispose()
    }
    //endregion

    /** Setup accounts disposable and listen for data */
    private fun setupAccounts() {
        accountsDisposable = model.getAccounts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                view.submitAccounts(list)
            }, {
                view.showFailedToGetData()
            })
    }

    /** Called when a list item is clicked on the view */
    fun onItemClick(): (Account) -> Unit = { account ->
        view.openAccountHistoryView(account)
    }
}