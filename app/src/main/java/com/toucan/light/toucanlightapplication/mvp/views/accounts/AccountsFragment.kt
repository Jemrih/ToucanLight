package com.toucan.light.toucanlightapplication.mvp.views.accounts


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.injection.scopes.PerFragment
import com.toucan.light.toucanlightapplication.mvp.ToucanLightApplication
import com.toucan.light.toucanlightapplication.mvp.core.BaseActivity
import com.toucan.light.toucanlightapplication.mvp.core.BaseFragment
import com.toucan.light.toucanlightapplication.mvp.views.accounts.impl.AccountsFragmentInteractor
import com.toucan.light.toucanlightapplication.mvp.views.accounts.impl.AccountsFragmentListAdapter
import com.toucan.light.toucanlightapplication.mvp.views.accounts.impl.AccountsFragmentModel
import com.toucan.light.toucanlightapplication.mvp.views.accounts.impl.AccountsFragmentPresenter
import com.toucan.light.toucanlightapplication.mvp.views.accounts.impl.AccountsFragmentView
import com.toucan.light.toucanlightapplication.mvp.views.history.HistoryActivity
import com.toucan.light.toucanlightapplication.repository.AccountRepository
import com.toucan.light.toucanlightapplication.storage.entity.Account
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.android.synthetic.main.fragment_accounts.*
import javax.inject.Inject

class AccountsFragment : BaseFragment(), AccountsFragmentView {

    //region Members
    @Inject
    lateinit var presenter: AccountsFragmentPresenter
    private lateinit var adapter: AccountsFragmentListAdapter
    //endregion

    //region Lifecycle
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_accounts, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AccountsFragmentListAdapter(presenter.onItemClick())
        rv_accounts_list.adapter = adapter
        presenter.initialize()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        presenter.onPause()
        super.onPause()
    }

    companion object {
        fun newInstance(): AccountsFragment = AccountsFragment()
    }
    //endregion

    override fun submitAccounts(accounts: List<Account>) {
        adapter.submitList(accounts)
    }

    override fun showFailedToGetData() {

    }

    override fun openAccountHistoryView(account: Account) {
        val intent = Intent(context, HistoryActivity::class.java)
        context?.startActivity(intent)
    }

    //region Dagger injection components
    private fun getComponent(): AccountsFragmentComponent {
        return ((context as BaseActivity).application as ToucanLightApplication).getComponents(
            context as BaseActivity
        )!!.addCommentFragment(
            MvpModule(this)
        )
    }

    interface Injects {
        fun addCommentFragment(module: MvpModule): AccountsFragmentComponent
    }

    @Subcomponent(modules = [MvpModule::class])
    @PerFragment
    interface AccountsFragmentComponent {
        fun inject(Fragment: AccountsFragment)
    }

    @Module
    @PerFragment
    class MvpModule(private val view: AccountsFragmentView) {
        @Provides
        internal fun providesView(): AccountsFragmentView {
            return view
        }

        @Provides
        internal fun providesPresenter(
            view: AccountsFragmentView,
            model: AccountsFragmentModel,
            interactor: AccountsFragmentInteractor
        ): AccountsFragmentPresenter {
            return AccountsFragmentPresenter(view, model, interactor)
        }

        @Provides
        internal fun providesInteractor(): AccountsFragmentInteractor {
            return AccountsFragmentInteractor()
        }

        @Provides
        internal fun providesModel(repository: AccountRepository): AccountsFragmentModel {
            return AccountsFragmentModel(repository)
        }
    }
    //endregion
}
