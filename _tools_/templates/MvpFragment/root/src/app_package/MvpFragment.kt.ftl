package ${packageName}

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
import ${packageName}.impl.${className}FragmentView
import ${packageName}.impl.${className}FragmentPresenter
import ${packageName}.impl.${className}FragmentModel
import ${packageName}.impl.${className}FragmentInteractor
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import kotlinx.android.synthetic.main.${fragment_layout}.*
import javax.inject.Inject

class ${className}Fragment : BaseFragment(), ${className}FragmentView {

    //region Members
    @Inject
    lateinit var presenter: ${className}FragmentPresenter
    //endregion

    //region Lifecycle
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        getComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.${fragment_layout}, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        fun newInstance(): ${className}Fragment = ${className}Fragment()
    }
    //endregion

    //region Dagger injection components
    private fun getComponent(): ${className}FragmentComponent {
        return ((context as BaseActivity).application as ToucanLightApplication).getComponents(
            context as BaseActivity
        )!!.add${className}Fragment(
            MvpModule(this)
        )
    }

    interface Injects {
        fun add${className}Fragment(module: MvpModule): ${className}FragmentComponent
    }

    @Subcomponent(modules = [MvpModule::class])
    @PerFragment
    interface ${className}FragmentComponent {
        fun inject(Fragment: ${className}Fragment)
    }

    @Module
    @PerFragment
    class MvpModule(private val view: ${className}FragmentView) {
        @Provides
        internal fun providesView(): ${className}FragmentView {
            return view
        }

        @Provides
        internal fun providesPresenter(
            view: ${className}FragmentView,
            model: ${className}FragmentModel,
            interactor: ${className}FragmentInteractor
        ): ${className}FragmentPresenter {
            return ${className}FragmentPresenter(view, model, interactor)
        }

        @Provides
        internal fun providesInteractor(): ${className}FragmentInteractor {
            return ${className}FragmentInteractor()
        }

        @Provides
        internal fun providesModel(): ${className}FragmentModel {
            return ${className}FragmentModel()
        }
    }
    //endregion
}