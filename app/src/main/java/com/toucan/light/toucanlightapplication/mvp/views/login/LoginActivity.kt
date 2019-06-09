package com.toucan.light.toucanlightapplication.mvp.views.login

import android.os.Bundle
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.mvp.core.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    //region Members
    lateinit var fragment: LoginFragment
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fragment = LoginFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            add(fl_view_login_main_frame.id, fragment)
        }.commit()
    }
}
