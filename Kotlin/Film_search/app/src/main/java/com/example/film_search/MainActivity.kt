package com.example.film_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.film_search.fragments.InfoFragment
import com.example.film_search.fragments.MovieListFragment
import com.example.film_search.listeners.interfaces.ActivityNavigationListeners
import com.example.film_search.utils.HelpUtils

class MainActivity : AppCompatActivity(),
    ActivityNavigationListeners {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState.let {
            HelpUtils.replaceFragment(
                this,
                MovieListFragment.newInstance(),
                MovieListFragment.TAG,
                false
            )
        }
    }

    override fun showSingleMoviePage(movieId: String) {
        HelpUtils.replaceFragment(
            this,
            InfoFragment.newInstance(movieId),
            InfoFragment.TAG,
            true
        )
    }
}