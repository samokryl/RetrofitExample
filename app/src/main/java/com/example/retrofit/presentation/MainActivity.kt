package com.example.retrofit.presentation

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.data.network.RetrofitApi
import com.example.retrofit.di.DI
import com.example.retrofit.domain.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

	lateinit var retrofitApi: RetrofitApi
	lateinit var layoutManager: LinearLayoutManager
	lateinit var adapter: MyMovieAdapter
	lateinit var recyclerMovieList: RecyclerView
	lateinit var dialog: ProgressDialog

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		recyclerMovieList = findViewById<RecyclerView>(R.id.recyclerMovieList)

		retrofitApi = DI.retrofitApi
		recyclerMovieList.setHasFixedSize(true)
		layoutManager = LinearLayoutManager(this)
		recyclerMovieList.layoutManager = layoutManager

		dialog = ProgressDialog(this@MainActivity)


		getAllMovieList()
	}

	private fun getAllMovieList() {
		dialog.show()
		retrofitApi.getMovieList().enqueue(object : Callback<MutableList<Movie>> {
			override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {
				Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
			}

			override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
				adapter = MyMovieAdapter(baseContext, response.body() as MutableList<Movie>)
				adapter.notifyDataSetChanged()
				recyclerMovieList.adapter = adapter

				dialog.dismiss()
			}
		})
	}
}
