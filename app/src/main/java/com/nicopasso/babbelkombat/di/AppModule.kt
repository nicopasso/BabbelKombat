package com.nicopasso.babbelkombat.di

import com.nicopasso.babbelkombat.BKApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class AppModule(val app: BKApplication) {

    @Provides @Singleton fun provideApp() = app

}