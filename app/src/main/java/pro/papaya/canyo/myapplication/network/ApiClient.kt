package pro.papaya.canyo.myapplication.network

import android.content.Context
import android.widget.Toast
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.papaya.canyo.myapplication.model.Joke

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
                    .baseUrl("https://api.chucknorris.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()

            api = retrofit!!.create<Api>(Api::class.java) //Создаем объект, при помощи которого будем выполнять запросы
        }

        fun getJoke(context: Context){
            val messages = api!!.getJoke()

            messages.enqueue(object : Callback<Joke> {
                override fun onFailure(call: Call<Joke>?, t: Throwable?) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Joke>?, response: Response<Joke>?) {
                    Toast.makeText(context, response?.body()?.value, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}