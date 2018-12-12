package pro.papaya.canyo.sportify.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.papaya.canyo.sportify.model.BaseResponse
import pro.papaya.canyo.sportify.model.Joke
import pro.papaya.canyo.sportify.model.RegisterBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        var api: Api? = null
            private set

        fun initApi() {
            val logLevel = HttpLoggingInterceptor.Level.BODY
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = logLevel
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl("http://18.220.223.146:5555/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

            api = retrofit!!.create<Api>(Api::class.java) //Создаем объект, при помощи которого будем выполнять запросы
        }

        fun getJoke(): Call<Joke> {
            return api!!.getJoke()
        }

        fun register(body: RegisterBody): Call<BaseResponse> {
            return api!!.register(body)
        }
    }
}