package com.example.retrofit.di

import com.example.retrofit.data.network.RetrofitApi
import com.example.retrofit.data.network.RetrofitClient

object DI {

	private const val BASE_URL = "https://www.simplifiedcoding.net/demos/"

	val retrofitApi: RetrofitApi by lazy { RetrofitClient.getClient(BASE_URL).create(RetrofitApi::class.java) }

}
