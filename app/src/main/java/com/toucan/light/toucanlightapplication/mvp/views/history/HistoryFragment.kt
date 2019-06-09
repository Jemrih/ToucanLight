package com.toucan.light.toucanlightapplication.mvp.views.history


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.injection.scopes.PerFragment
import com.toucan.light.toucanlightapplication.mvp.ToucanLightApplication
import com.toucan.light.toucanlightapplication.mvp.core.BaseActivity
import com.toucan.light.toucanlightapplication.mvp.core.BaseFragment
import com.toucan.light.toucanlightapplication.mvp.views.history.impl.HistoryFragmentInteractor
import com.toucan.light.toucanlightapplication.mvp.views.history.impl.HistoryFragmentListAdapter
import com.toucan.light.toucanlightapplication.mvp.views.history.impl.HistoryFragmentModel
import com.toucan.light.toucanlightapplication.mvp.views.history.impl.HistoryFragmentPresenter
import com.toucan.light.toucanlightapplication.mvp.views.history.impl.HistoryFragmentView
import com.toucan.light.toucanlightapplication.mvp.views.history.impl.HistoryListTypes
import com.toucan.light.toucanlightapplication.repository.TransactionHistoryRepository
import com.toucan.light.toucanlightapplication.storage.entity.TransactionHistory
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

class HistoryFragment : BaseFragment(), HistoryFragmentView {

    //region Members
    @Inject
    lateinit var presenter: HistoryFragmentPresenter
    private lateinit var adapter: HistoryFragmentListAdapter
    //endregion

    //region Lifecycle
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HistoryFragmentListAdapter(presenter.onListItemClicked())
        rv_history_list.adapter = adapter

        setupToolbar()

        presenter.initialize(
            getString(R.string.history_view_upcoming_transaction_title),
            getString(R.string.history_view_upcoming_transaction_description)
        )
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
        fun newInstance(): HistoryFragment = HistoryFragment()
    }
    //endregion

    private fun setupToolbar() {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(t_history_toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    override fun showErrorNotice() {

    }

    override fun submitTransactionHistory(list: List<HistoryListTypes>) {
        adapter.submitList(list)
    }

    //region Dagger injection components
    private fun getComponent(): HistoryFragmentComponent {
        return ((context as BaseActivity).application as ToucanLightApplication).getComponents(
            context as BaseActivity
        )!!.addCommentFragment(
            MvpModule(this)
        )
    }

    interface Injects {
        fun addCommentFragment(module: MvpModule): HistoryFragmentComponent
    }

    @Subcomponent(modules = [MvpModule::class])
    @PerFragment
    interface HistoryFragmentComponent {
        fun inject(Fragment: HistoryFragment)
    }

    @Module
    @PerFragment
    class MvpModule(private val view: HistoryFragmentView) {
        @Provides
        internal fun providesView(): HistoryFragmentView {
            return view
        }

        @Provides
        internal fun providesPresenter(
            view: HistoryFragmentView,
            model: HistoryFragmentModel,
            interactor: HistoryFragmentInteractor
        ): HistoryFragmentPresenter {
            return HistoryFragmentPresenter(view, model, interactor)
        }

        @Provides
        internal fun providesInteractor(): HistoryFragmentInteractor {
            return HistoryFragmentInteractor()
        }

        @Provides
        internal fun providesModel(repository: TransactionHistoryRepository): HistoryFragmentModel {
            return HistoryFragmentModel(repository)
        }
    }
    //endregion
}
