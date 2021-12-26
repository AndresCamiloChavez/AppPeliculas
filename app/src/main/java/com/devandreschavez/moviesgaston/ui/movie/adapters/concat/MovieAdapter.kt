package com.devandreschavez.moviesgaston.ui.movie.adapters.concat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devandreschavez.moviesgaston.core.BaseViewHolder
import com.devandreschavez.moviesgaston.data.model.Movie
import com.devandreschavez.moviesgaston.databinding.MovieItemBinding

//*representar cualquier tipo de viewHolder
class MovieAdapter(
    private val movieList: List<Movie>,
    private val itemClickListener: onMovieClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface onMovieClickListener {
        fun onMovieClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size

    private inner class MoviesViewHolder(
        val binding: MovieItemBinding,
        val context: Context
    ) : BaseViewHolder<Movie>(binding.root) {

        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500${item.poster_path}")
                .centerCrop().into(binding.imgItem)
        }
    }

}