package com.kozitskiy.shoppinglist.dagger

import com.kozitskiy.shoppinglist.MainActivity
import com.kozitskiy.shoppinglist.cache.CacheRepository
import com.kozitskiy.shoppinglist.dagger.modules.AdapterModule
import com.kozitskiy.shoppinglist.dagger.modules.BaseModule
import com.kozitskiy.shoppinglist.dagger.modules.DbModule
import com.kozitskiy.shoppinglist.management.ManagerActualPurchases
import com.kozitskiy.shoppinglist.management.ManagerAddingPurchases
import com.kozitskiy.shoppinglist.management.ManagerCompletedPurchases
import com.kozitskiy.shoppinglist.ui.ActualPurchasesFragment
import com.kozitskiy.shoppinglist.ui.AddingPurchasesFragment
import com.kozitskiy.shoppinglist.ui.CompletedPurchasesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DbModule::class, BaseModule::class, AdapterModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(cacheRepository: CacheRepository)
    fun inject(managerAddingPurchases: ManagerAddingPurchases)
    fun inject(managerActualPurchases: ManagerActualPurchases)
    fun inject(managerCompletedPurchases: ManagerCompletedPurchases)
    fun inject(completedPurchasesFragment: CompletedPurchasesFragment)
    fun inject(addingPurchasesFragment: AddingPurchasesFragment)
    fun inject(actualPurchasesFragment: ActualPurchasesFragment)
}
