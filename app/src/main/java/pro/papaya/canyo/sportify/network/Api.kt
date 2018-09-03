package pro.papaya.canyo.sportify.network

import pro.papaya.canyo.sportify.model.BaseResponse
import pro.papaya.canyo.sportify.model.Joke
import pro.papaya.canyo.sportify.model.RegisterBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("jokes/random")
    fun getJoke(): Call<Joke>

    @POST("clients/me/registration")
    fun register(
            @Body registerBody: RegisterBody
    ): Call<BaseResponse>
}