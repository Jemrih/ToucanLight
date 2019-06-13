package ${packageName}

import android.os.Bundle
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.mvp.core.BaseActivity
import kotlinx.android.synthetic.main.${activity_layout}.*

//TODO: Dont forget to add activity to manifest
class ${className}Activity : BaseActivity() {

    //region Members
    lateinit var fragment: ${className}Fragment
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${activity_layout})

        fragment = ${className}Fragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            add(fl_view_${className}_main_frame.id, fragment)
        }.commit()
    }
    //endregion
}