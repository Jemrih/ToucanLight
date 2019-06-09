package com.toucan.light.toucanlightapplication.mvp.views.main.impl

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.toucan.light.toucanlightapplication.R
import com.toucan.light.toucanlightapplication.mvp.views.accounts.AccountsFragment
import com.toucan.light.toucanlightapplication.mvp.views.loans.LoansFragment

class MainPagerAdapter(
    fragmentManager: FragmentManager?,
    private val context: Context?
) : FragmentPagerAdapter(fragmentManager) {
    //region Members
    private var fragments = listOf(
        AccountsFragment.newInstance(),
        LoansFragment.newInstance()
    )

    private var pageTitles = listOf(
        R.string.accounts_view_title,
        R.string.loans_view_title
    )
    //endregion

    override fun getCount(): Int = fragments.size
    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getPageTitle(position: Int): CharSequence? = context?.getString(pageTitles[position])
}