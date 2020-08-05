package com.kozitskiy.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.kozitskiy.shoppinglist.adapters.PagerAdapter
import com.kozitskiy.shoppinglist.interfaces.ActivityNavigation
import com.kozitskiy.shoppinglist.ui.ActualPurchasesFragment
import com.kozitskiy.shoppinglist.ui.AddingPurchasesFragment
import com.kozitskiy.shoppinglist.ui.CompletedPurchasesFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(),
    ActivityNavigation {

    private lateinit var mViewPager: ViewPager

    @Inject
    lateinit var addingPurchasesFragment: AddingPurchasesFragment

    @Inject
    lateinit var actualPurchasesFragment: ActualPurchasesFragment

    @Inject
    lateinit var completedPurchasesFragment: CompletedPurchasesFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.appComponent.inject(this)

        //Setup viewpager
        setupViewPager(viewpager)
        header_tabs.setupWithViewPager(viewpager)
    }

    override fun navigateToAddingPurchasePage() {
        mViewPager.setCurrentItem(0, true)
    }

    //Add Fragments to Tabs with their names
    private fun setupViewPager(viewPager: ViewPager) {
        mViewPager = viewPager
        val pagerAdapter = PagerAdapter(supportFragmentManager)

        pagerAdapter.addFragment(addingPurchasesFragment, HEAD_ITEM_ADD_NAME)
        pagerAdapter.addFragment(actualPurchasesFragment, HEAD_ITEM_ACTUAL_NAME)
        pagerAdapter.addFragment(completedPurchasesFragment, HEAD_ITEM_COMPLETED_NAME)
        viewPager.adapter = pagerAdapter
        viewPager.currentItem = 1
        viewPager.offscreenPageLimit = pagerAdapter.count
    }
}