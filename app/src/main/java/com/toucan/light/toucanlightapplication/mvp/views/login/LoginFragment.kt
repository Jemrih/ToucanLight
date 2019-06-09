package com.toucan.light.toucanlightapplication.mvp.views.login

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
import com.toucan.light.toucanlightapplication.mvp.views.login.impl.LoginFragmentInteractor
import com.toucan.light.toucanlightapplication.mvp.views.login.impl.LoginFragmentModel
import com.toucan.light.toucanlightapplication.mvp.views.login.impl.LoginFragmentPresenter
import com.toucan.light.toucanlightapplication.mvp.views.login.impl.LoginFragmentView
import com.toucan.light.toucanlightapplication.mvp.views.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment(), LoginFragmentView {

    //region Members
    @Inject
    lateinit var presenter: LoginFragmentPresenter
    //endregion

    //region Lifecycle
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
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
        fun newInstance(): LoginFragment = LoginFragment()
    }
    //endregion

    private fun setupListeners() {
        btn_view_login_primary.setOnClickListener {
            presenter.onLoginButtonClicked()
        }

        txt_view_login_contact.setOnClickListener {
            presenter.onContactUsClicked()
        }
    }

    override fun navigateToMainFlow() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun showContactUsDialog() { }

    //region Dagger injection components
    private fun getComponent(): LoginFragmentComponent {
        return ((context as BaseActivity).application as ToucanLightApplication).getComponents(
            context as BaseActivity
        )!!.addCommentFragment(
            MvpModule(this)
        )
    }

    interface Injects {
        fun addCommentFragment(module: MvpModule): LoginFragmentComponent
    }

    @Subcomponent(modules = [MvpModule::class])
    @PerFragment
    interface LoginFragmentComponent {
        fun inject(Fragment: LoginFragment)
    }

    @Module
    @PerFragment
    class MvpModule(private val view: LoginFragmentView) {
        @Provides
        internal fun providesView(): LoginFragmentView {
            return view
        }

        @Provides
        internal fun providesPresenter(
            view: LoginFragmentView,
            model: LoginFragmentModel,
            interactor: LoginFragmentInteractor
        ): LoginFragmentPresenter {
            return LoginFragmentPresenter(view, model, interactor)
        }

        @Provides
        internal fun providesInteractor(): LoginFragmentInteractor {
            return LoginFragmentInteractor()
        }

        @Provides
        internal fun providesModel(): LoginFragmentModel {
            return LoginFragmentModel()
        }
    }
    //endregion
}
