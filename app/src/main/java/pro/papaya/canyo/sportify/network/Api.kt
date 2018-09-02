package pro.papaya.canyo.sportify.network

import pro.papaya.canyo.sportify.model.Joke
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("jokes/random")
    fun getJoke(): Call<Joke>
}