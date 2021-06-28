package com.techlads.demoapp.ui.listing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techlads.demoapp.Config
import com.techlads.demoapp.ui.details.DetailsActivity
import com.techlads.demoapp.utils.Genre
import com.techlads.demoapp.R
import com.techlads.demoapp.model.Movie
import kotlinx.android.synthetic.main.list_item_movies.view.*

class MoviesAdapter(private val context: Context, private val list: ArrayList<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){


    class MoviesViewHolder(private val context: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

            fun bind(movie: Movie) {
                itemView.setOnClickListener {
                    DetailsActivity.start(context, movie.id)
                }
                itemView.tvTitle.text = movie.title
                Glide.with(context).load(Config.IMAGE_URL + movie.poster_path)
                    .apply(RequestOptions().override(400, 400).centerInside()
                        .placeholder(R.drawable.placehoder)).into(itemView.ivPoster)
                itemView.tvGenre.text = Genre.getGenre(movie.genre_ids)

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.list_item_movies, parent, false)
        return MoviesViewHolder(context, view)

    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<Movie>?) {
        list.clear()
        val sortedList = newList?.sortedBy { movie -> movie.popularity }
        sortedList?.let { list.addAll(it) }
        notifyDataSetChanged()
    }

}