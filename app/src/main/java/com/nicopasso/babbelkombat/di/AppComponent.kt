package com.nicopasso.babbelkombat.di

import com.nicopasso.babbelkombat.BKApplication
import dagger.Component
import javax.inject.Singleton

@Singleton @Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: BKApplication)

    fun plus(startActivityModule: StartActivityModule): StartActivityComponent

}