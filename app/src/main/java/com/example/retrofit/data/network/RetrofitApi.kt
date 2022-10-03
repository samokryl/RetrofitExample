package com.example.retrofit.data.network

import com.example.retrofit.domain.model.Movie
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

	@GET("marvel")
	fun getMovieList(): Call<MutableList<Movie>>
}