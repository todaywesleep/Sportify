package pro.papaya.canyo.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import pro.papaya.canyo.myapplication.network.ApiClient

class HelloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        ApiClient.initApi()
        ApiClient.getJoke(this)
    }
}
