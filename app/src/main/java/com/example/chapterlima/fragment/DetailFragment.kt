package com.example.chapterlima.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.chapterlima.R
import com.example.chapterlima.databinding.FragmentDetailBinding
import com.example.chapterlima.model.Movie
import com.example.chapterlima.viewModel.MovieViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    lateinit var filmViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // pengambilan data
        val list = arguments?.getParcelable<Movie>("data_movie") as Movie
        val title = list.title
        val date = list.releaseDate
        val overview = list.overview
        val imagepath = list.imagePath

        binding.tvTitle.text = title
        binding.tvTglRelease.text = date
        binding.tvDesc.text = overview
        Glide.with(view.context).load("https://image.tmdb.org/t/p/w780${imagepath}").into(binding.imgMovie)

    }


}