package pro.papaya.canyo.sportify.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.network.ApiClient

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ApiClient.initApi()
        ApiClient.getJoke(this)
    }
}
