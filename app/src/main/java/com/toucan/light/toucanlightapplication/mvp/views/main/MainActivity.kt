package com.toucan.light.toucanlightapplication.mvp.views.main

import android.os.Bundle
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.mvp.core.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    //region Members
    lateinit var fragment: MainFragment
    //endregion

    //region Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            add(fl_view_main_frame.id, fragment)
        }.commit()
    }
    //endregion
}