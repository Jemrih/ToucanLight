package com.toucan.light.toucanlightapplication.mvp.views.main


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.injection.scopes.PerFragment
import com.toucan.light.toucanlightapplication.mvp.ToucanLightApplication
import com.toucan.light.toucanlightapplication.mvp.core.BaseActivity
import com.toucan.light.toucanlightapplication.mvp.core.BaseFragment
import com.toucan.light.toucanlightapplication.mvp.views.main.impl.MainFragmentInteractor
import com.toucan.light.toucanlightapplication.mvp.views.main.impl.MainFragmentModel
import com.toucan.light.toucanlightapplication.mvp.views.main.impl.MainFragmentPresenter
import com.toucan.light.toucanlightapplication.mvp.views.main.impl.MainFragmentView
import com.toucan.light.toucanlightapplication.mvp.views.main.impl.MainPagerAdapter
import com.toucan.light.toucanlightapplication.utils.extensions.gone
import com.toucan.light.toucanlightapplication.utils.extensions.visible
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment(), MainFragmentView {

    //region Members
    @Inject
    lateinit var presenter: MainFragmentPresenter
    private lateinit var adapter: MainPagerAdapter
    //endregion

    //region Lifecycle
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
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
        fun newInstance(): MainFragment = MainFragment()
    }
    //endregion

    private fun setupViewPager() {
        adapter = MainPagerAdapter(fragmentManager, context)
        vp_main_pager.adapter = adapter
        tl_main_tabs.setupWithViewPager(vp_main_pager)

        vp_main_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) { /** Empty */ }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //Only show fab if in first tab
                if (position == 0) {
                    fab_main_start_transaction.visible()
                    fab_main_start_transaction.alpha = 1F - positionOffset
                } else {
                    fab_main_start_transaction.gone()
                }
            }

            override fun onPageSelected(position: Int) { /** Empty */ }
        })
    }

    //region Dagger injection components
    private fun getComponent(): MainFragmentComponent {
        return ((context as BaseActivity).application as ToucanLightApplication).getComponents(
            context as BaseActivity
        )!!.addCommentFragment(
            MvpModule(this)
        )
    }

    interface Injects {
        fun addCommentFragment(module: MvpModule): MainFragmentComponent
    }

    @Subcomponent(modules = [MvpModule::class])
    @PerFragment
    interface MainFragmentComponent {
        fun inject(Fragment: MainFragment)
    }

    @Module
    @PerFragment
    class MvpModule(private val view: MainFragmentView) {
        @Provides
        internal fun providesView(): MainFragmentView {
            return view
        }

        @Provides
        internal fun providesPresenter(
            view: MainFragmentView,
            model: MainFragmentModel,
            interactor: MainFragmentInteractor
        ): MainFragmentPresenter {
            return MainFragmentPresenter(view, model, interactor)
        }

        @Provides
        internal fun providesInteractor(): MainFragmentInteractor {
            return MainFragmentInteractor()
        }

        @Provides
        internal fun providesModel(): MainFragmentModel {
            return MainFragmentModel()
        }
    }
    //endregion
}
