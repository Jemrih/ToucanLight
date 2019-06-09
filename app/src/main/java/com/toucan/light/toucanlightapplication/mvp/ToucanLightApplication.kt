package com.toucan.light.toucanlightapplication.mvp

import android.app.Application
import android.content.Context
import com.toucan.light.toucanlightapplication.injection.components.DaggerToucanApplicationComponent
import com.toucan.light.toucanlightapplication.injection.components.ToucanApplicationComponent
import com.toucan.light.toucanlightapplication.injection.modules.ToucanApplicationModule

class ToucanLightApplication: Application() {

    private var components: ToucanApplicationComponent? = null

    fun getComponents(context: Context): ToucanApplicationComponent? {
        return (context.applicationContext as ToucanLightApplication).components
    }

    override fun onCreate() {
        components = initializeDependencies()
        super.onCreate()
    }

    private fun newBuilder(): ToucanApplicationComponent.Builder {
        return DaggerToucanApplicationComponent.builder()
    }


    private fun initializeDependencies(): ToucanApplicationComponent {
        return configureDependencies(newBuilder()).build()
    }

    private fun configureDependencies(builder: ToucanApplicationComponent.Builder): ToucanApplicationComponent.Builder {
        return builder.applicationModule(ToucanApplicationModule(this))
    }

}