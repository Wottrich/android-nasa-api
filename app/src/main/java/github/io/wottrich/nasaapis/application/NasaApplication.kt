package github.io.wottrich.nasaapis.application

import android.app.Application
import github.io.wottrich.nasaapis.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Wottrich
 * @author wottrich78@gmail.com
 * @since 05/01/2022
 *
 * Copyright © 2022 NasaApis. All rights reserved.
 *
 */

class NasaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NasaApplication)
            modules(appModule)
        }
    }

}