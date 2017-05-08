package com.nicopasso.babbelkombat

import android.app.Application
import com.nicopasso.babbelkombat.di.AppComponent
import com.nicopasso.babbelkombat.di.AppModule
import com.nicopasso.babbelkombat.di.DaggerAppComponent

class BKApplication: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}