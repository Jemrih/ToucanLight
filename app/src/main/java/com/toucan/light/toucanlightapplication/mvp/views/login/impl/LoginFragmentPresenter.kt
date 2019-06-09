package com.toucan.light.toucanlightapplication.mvp.views.login.impl

class LoginFragmentPresenter(
    private val view: LoginFragmentView,
    private val model: LoginFragmentModel,
    private val interactor: LoginFragmentInteractor
) {

    //region Presenter Lifecycle
    fun initialize() {

    }

    fun onResume() {

    }

    fun onPause() {

    }
    //endregion

    fun onLoginButtonClicked() {
        view.navigateToMainFlow()
    }

    fun onContactUsClicked() {
        view.showContactUsDialog()
    }
}