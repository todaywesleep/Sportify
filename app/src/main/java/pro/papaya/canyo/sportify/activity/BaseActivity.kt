package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import pro.papaya.canyo.myapplication.R

abstract class BaseActivity: AppCompatActivity(){
    private lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        super.setContentView(R.layout.activity_base)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        container = findViewById(R.id.base_container)
    }

    override fun setContentView(layoutId: Int){
        val child = layoutInflater.inflate(layoutId, null)
        container.addView(child)
    }
}