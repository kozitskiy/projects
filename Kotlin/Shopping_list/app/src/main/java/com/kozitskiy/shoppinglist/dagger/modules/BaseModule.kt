package com.kozitskiy.shoppinglist.dagger.modules

import com.kozitskiy.shoppinglist.management.handlers.ImageLoadHandler
import com.kozitskiy.shoppinglist.ui.ActualPurchasesFragment
import com.kozitskiy.shoppinglist.ui.AddingPurchasesFragment
import com.kozitskiy.shoppinglist.ui.CompletedPurchasesFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule() {
    @Provides
    @Singleton
    fun provideAddingFragment() = AddingPurchasesFragment()

    @Provides
    @Singleton
    fun provideActualFragment() = ActualPurchasesFragment()

    @Provides
    @Singleton
    fun provideCompletedFragment() = CompletedPurchasesFragment()

    @Provides
    @Singleton
    fun provideImageHandler(fragment: AddingPurchasesFragment) =
        ImageLoadHandler(fragment)
}