package com.example.midapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midapplication.databinding.ActivityMainBinding
import com.example.midapplication.domain.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        with(binding) {
            val customAdapter = adapter()
            recyclerView.adapter = customAdapter
            recyclerView.layoutManager = LinearLayoutManager(baseContext)
            //레트로핏
            val retrofit = Retrofit.Builder()
                .baseUrl("http://jiyoon04.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            //인터페이스 변환
            val intrude = retrofit.create(Intrude::class.java)

            button.setOnClickListener{
                intrude.intrudeuser().enqueue(object:Callback<Repository>{
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                        response.body()?.let { result ->
                            customAdapter.userlist = result
                            customAdapter.notifyDataSetChanged()
                        }
                    }

                    override fun onFailure(call: Call<Repository>, t: Throwable) {
                        Log.e("main activity", "$(t.localizedMessage)")
                    }
                })
            }
        }

    }
}

interface Intrude {
    @GET("api_root/Post/")
    fun intrudeuser() : Call<Repository>
}