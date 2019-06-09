package com.toucan.light.toucanlightapplication.mvp.views.loans


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.injection.scopes.PerFragment
import com.toucan.light.toucanlightapplication.mvp.ToucanLightApplication
import com.toucan.light.toucanlightapplication.mvp.core.BaseActivity
import com.toucan.light.toucanlightapplication.mvp.core.BaseFragment
import com.toucan.light.toucanlightapplication.mvp.views.loans.impl.LoansFragmentInteractor
import com.toucan.light.toucanlightapplication.mvp.views.loans.impl.LoansFragmentListAdapter
import com.toucan.light.toucanlightapplication.mvp.views.loans.impl.LoansFragmentModel
import com.toucan.light.toucanlightapplication.mvp.views.loans.impl.LoansFragmentPresenter
import com.toucan.light.toucanlightapplication.mvp.views.loans.impl.LoansFragmentView
import com.toucan.light.toucanlightapplication.repository.LoanRepository
import com.toucan.light.toucanlightapplication.storage.entity.Loan
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.android.synthetic.main.fragment_loans.*
import javax.inject.Inject

class LoansFragment : BaseFragment(), LoansFragmentView {

    //region Members
    @Inject
    lateinit var presenter: LoansFragmentPresenter
    lateinit var adapter: LoansFragmentListAdapter
    //endregion

    //region Lifecycle
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_loans, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LoansFragmentListAdapter(presenter.onItemClick())
        rv_loans_list.adapter = adapter

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
        fun newInstance(): LoansFragment = LoansFragment()
    }
    //endregion

    override fun submitLoans(list: List<Loan>) {
        adapter.submitList(list)
    }

    override fun showFailedToGetData() {

    }

    //region Dagger injection components
    private fun getComponent(): LoansFragmentComponent {
        return ((context as BaseActivity).application as ToucanLightApplication).getComponents(
            context as BaseActivity
        )!!.addCommentFragment(
            MvpModule(this)
        )
    }

    interface Injects {
        fun addCommentFragment(module: MvpModule): LoansFragmentComponent
    }

    @Subcomponent(modules = [MvpModule::class])
    @PerFragment
    interface LoansFragmentComponent {
        fun inject(Fragment: LoansFragment)
    }

    @Module
    @PerFragment
    class MvpModule(private val view: LoansFragmentView) {
        @Provides
        internal fun providesView(): LoansFragmentView {
            return view
        }

        @Provides
        internal fun providesPresenter(
            view: LoansFragmentView,
            model: LoansFragmentModel,
            interactor: LoansFragmentInteractor
        ): LoansFragmentPresenter {
            return LoansFragmentPresenter(view, model, interactor)
        }

        @Provides
        internal fun providesInteractor(): LoansFragmentInteractor {
            return LoansFragmentInteractor()
        }

        @Provides
        internal fun providesModel(loansRepository: LoanRepository): LoansFragmentModel {
            return LoansFragmentModel(loansRepository)
        }
    }
    //endregion
}
