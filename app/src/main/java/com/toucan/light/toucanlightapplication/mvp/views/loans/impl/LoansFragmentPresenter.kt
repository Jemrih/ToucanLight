package com.toucan.light.toucanlightapplication.mvp.views.loans.impl

import com.toucan.light.toucanlightapplication.storage.entity.Loan
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoansFragmentPresenter(
    private val view: LoansFragmentView,
    private val model: LoansFragmentModel,
    private val interactor: LoansFragmentInteractor
) {
    //region Members
    private var loansDisposable: Disposable? = null
    //endregion

    //region Presenter Lifecycle
    fun initialize() {

    }

    fun onResume() {
        setupLoans()
    }

    fun onPause() {
        loansDisposable?.dispose()
    }
    //endregion

    /** Setup accounts disposable and listen for data */
    private fun setupLoans() {
        loansDisposable = model.getLoans()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                view.submitLoans(list)
            }, {
                view.showFailedToGetData()
            })
    }

    fun onItemClick(): (Loan) -> Unit = {

    }
}