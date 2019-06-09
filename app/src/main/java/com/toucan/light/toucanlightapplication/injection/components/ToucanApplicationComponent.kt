package com.toucan.light.toucanlightapplication.injection.components

import com.toucan.light.toucanlightapplication.injection.modules.DaoModule
import com.toucan.light.toucanlightapplication.injection.modules.RepositoryModule
import com.toucan.light.toucanlightapplication.injection.modules.ToucanApplicationModule
import com.toucan.light.toucanlightapplication.injection.scopes.PerApp
import com.toucan.light.toucanlightapplication.mvp.views.accounts.AccountsFragment
import com.toucan.light.toucanlightapplication.mvp.views.history.HistoryFragment
import com.toucan.light.toucanlightapplication.mvp.views.loans.LoansFragment
import com.toucan.light.toucanlightapplication.mvp.views.login.LoginFragment
import com.toucan.light.toucanlightapplication.mvp.views.main.MainActivity
import com.toucan.light.toucanlightapplication.mvp.views.main.MainFragment

import dagger.Component

@PerApp
@Component(modules = [ToucanApplicationModule::class, DaoModule::class, RepositoryModule::class])
interface ToucanApplicationComponent:
/** Implement view/component here */
    MainFragment.Injects,
    AccountsFragment.Injects,
    LoansFragment.Injects,
    LoginFragment.Injects,
    HistoryFragment.Injects {

    @Component.Builder
    interface Builder {
        fun build(): ToucanApplicationComponent

        fun applicationModule(applicationModule: ToucanApplicationModule): Builder

        fun daoModule(daoModule: DaoModule): Builder

        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}
