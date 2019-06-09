package com.toucan.light.toucanlightapplication.mvp.views.history

import android.os.Bundle
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.mvp.core.BaseActivity
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : BaseActivity() {

    //region Members
    lateinit var fragment: HistoryFragment
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        fragment = HistoryFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            add(fl_view_history_main_frame.id, fragment)
        }.commit()
    }
    //endregion
}