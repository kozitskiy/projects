package com.example.film_search.network.manageRequests

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_info.*

abstract class ApiRequest(
    protected val fragment: Fragment,
    private val viewConnection: View? = null
) {
    abstract fun doRequest(): Disposable

    protected abstract fun <T> success(moviesData: T)

    protected fun error(t: Throwable) {
        viewConnection?.let {
            fragment.movie_element.removeAllViews()
            fragment.movie_element.addView(it)
        } ?: Toast.makeText(fragment.context, "Error Data", Toast.LENGTH_SHORT).show()
        t.printStackTrace()
    }
}