package com.toucan.light.toucanlightapplication.injection.modules

import android.content.Context
import android.content.res.Resources
import com.toucan.light.toucanlightapplication.injection.scopes.PerApp
import com.toucan.light.toucanlightapplication.mvp.ToucanLightApplication
import dagger.Module
import dagger.Provides

@Module
class ToucanApplicationModule(
    /** Reference on application instance  */
    private val application: ToucanLightApplication) {

    @Provides
    @PerApp
    internal fun providesApplication(): ToucanLightApplication {
        return application
    }

    @Provides
    @PerApp
    internal fun providesContext(): Context {
        return application.applicationContext
    }

    @Provides
    @PerApp
    internal fun providesResources(context: Context): Resources {
        return context.resources
    }
}