package com.kozitskiy.shoppinglist.dagger.modules

import com.kozitskiy.shoppinglist.adapters.ActualPurchasesAdapter
import com.kozitskiy.shoppinglist.adapters.CompletedPurchasesAdapter
import com.kozitskiy.shoppinglist.ui.ActualPurchasesFragment
import com.kozitskiy.shoppinglist.ui.CompletedPurchasesFragment
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AdapterModule {

    @Provides
    @Singleton
    fun provideActualAdapter(fragment: ActualPurchasesFragment) =
        ActualPurchasesAdapter(fragment)

    @Provides
    @Singleton
    fun provideCompletedAdapter(fragment: CompletedPurchasesFragment) =
        CompletedPurchasesAdapter(fragment)
}