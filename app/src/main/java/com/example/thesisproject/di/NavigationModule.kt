package com.example.thesisproject.di

import androidx.fragment.app.FragmentActivity
import com.example.thesisproject.R
import com.example.thesisproject.presentation.base.navigation.NavRouter
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    private val cicerone: Cicerone<Router> = create()

    @Provides
    @Singleton
    fun provideRouter(): Router {
        return cicerone.router
    }

//    @Provides
//    @Singleton
//    fun provideNavigator(activity: FragmentActivity): Navigator = AppNavigator(activity, R.id.container)

    @Provides
    @Singleton
    fun provideNavigationHolder(): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

    @Provides
    @Singleton
    fun provideNavRouter(router: Router): NavRouter = NavRouter(router)

}