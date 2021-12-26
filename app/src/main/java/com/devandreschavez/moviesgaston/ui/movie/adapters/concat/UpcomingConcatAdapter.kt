package com.devandreschavez.moviesgaston.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devandreschavez.moviesgaston.core.BaseContactHolder
import com.devandreschavez.moviesgaston.databinding.PopularMoviesRowBinding
import com.devandreschavez.moviesgaston.databinding.UpcommingMoviesRowBinding

class UpcomingConcatAdapter(private val moviesAdapter: MovieAdapter): RecyclerView.Adapter<BaseContactHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseContactHolder<*> {
        val itemBinding = UpcommingMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseContactHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(moviesAdapter)
        }
    }
    override fun getItemCount(): Int = 1
    private inner class ConcatViewHolder(val binding: UpcommingMoviesRowBinding): BaseContactHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvComingMovies.adapter = adapter
        }

    }
}