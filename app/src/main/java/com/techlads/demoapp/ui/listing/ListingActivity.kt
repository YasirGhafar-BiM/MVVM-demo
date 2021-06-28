package com.techlads.demoapp.ui.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.techlads.demoapp.R
import com.techlads.demoapp.model.Movie
import com.techlads.demoapp.model.Result
import kotlinx.android.synthetic.main.activity_main.*

class ListingActivity : AppCompatActivity() {

    private val list = ArrayList<Movie>()
    private val viewModel by viewModels<ListingViewModel>()
    private lateinit var moviesAdapter: MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun subscribeUi() {
        viewModel.movieList.observe(this, Observer { result ->

            when (result.status) {
                    Result.Status.SUCCESS -> {
                        result.data?.movies.let { list ->
                            moviesAdapter.updateData(list)
                        }
                    loading.visibility = View.GONE
                    }

                    Result.Status.ERROR -> {
                        result.message?.let {
                            showError(it)
                        }
                        loading.visibility = View.GONE
                    }

                    Result.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
            }
        })
    }

    private fun showError(msg: String) {
        Snackbar.make(vParent, msg, Snackbar.LENGTH_INDEFINITE).setAction("Dismiss") {
        }.show()
    }
}