package pro.papaya.canyo.myapplication.network

import pro.papaya.canyo.myapplication.model.Joke
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("jokes/random")
    fun getJoke(): Call<Joke>
}