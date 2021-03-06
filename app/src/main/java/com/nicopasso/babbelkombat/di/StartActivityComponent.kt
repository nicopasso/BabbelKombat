package com.nicopasso.babbelkombat.di

import com.nicopasso.babbelkombat.ui.StartActivity
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton @Subcomponent(modules = arrayOf(StartActivityModule::class))
interface StartActivityComponent {
    fun inject(activity: StartActivity)
}