package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import pro.papaya.canyo.myapplication.R
import android.support.v4.widget.DrawerLayout
import android.view.Gravity

abstract class BaseActivity: AppCompatActivity(){
    private lateinit var container: FrameLayout
    private lateinit var drawerButton: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private val toolbarOnClickListener = View.OnClickListener {view ->
        when (view.id){
            R.id.base_drawer_menu_button -> toggleDrawer()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        super.setContentView(R.layout.activity_base)
        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        drawerButton = findViewById(R.id.base_drawer_menu_button)
        container = findViewById(R.id.base_container)
        drawerLayout = findViewById(R.id.drawer_layout)

        drawerButton.setOnClickListener(toolbarOnClickListener)
    }

    override fun setContentView(layoutId: Int){
        val child = layoutInflater.inflate(layoutId, null)
        container.addView(child)
    }

    private fun toggleDrawer(){
       if(!drawerLayout.isDrawerOpen(GravityCompat.START)){
           drawerLayout.openDrawer(Gravity.START, true)
       }else{
           drawerLayout.closeDrawer(Gravity.END, true)
       }
    }
}