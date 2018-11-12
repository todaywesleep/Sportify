package pro.papaya.canyo.sportify.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.activity.client.MainClientActivity
import pro.papaya.canyo.sportify.adapter.ClientItemsDrawerAdapter
import pro.papaya.canyo.sportify.adapter.TrainerItemsDrawerAdaper


abstract class BaseActivity : AppCompatActivity() {
  private lateinit var container: FrameLayout
  private lateinit var drawerButton: ImageView
  private lateinit var toolbar: Toolbar
  private lateinit var drawerLayout: DrawerLayout
  private lateinit var drawerList: ListView
  private lateinit var logoutButton: TextView

  private val onClickListener = View.OnClickListener { view ->
    when (view.id) {
      R.id.base_drawer_menu_button -> toggleDrawer()
      R.id.drawer_logout -> {
        val navIntent = Intent(this, LoginActivity::class.java)
        navIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(navIntent)
      }
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
    drawerList = findViewById(R.id.left_drawer)
    logoutButton = findViewById(R.id.drawer_logout)
    drawerList.adapter = getRequiredDrawerAdapter()

    drawerButton.setOnClickListener(onClickListener)
    logoutButton.setOnClickListener(onClickListener)

    setOnClickListeners()
    title = getToolbarTitle()
  }

  override fun setTitle(titleId: Int) {
    findViewById<TextView>(R.id.base_toolbar_title).setText(titleId)
  }

  override fun setTitle(title: CharSequence?) {
    findViewById<TextView>(R.id.base_toolbar_title).text = title
  }

  private fun getRequiredDrawerAdapter(): BaseAdapter{
    return if (isClientAccount()){
      ClientItemsDrawerAdapter(this@BaseActivity, generateDrawerItems(true))
    }else{
      TrainerItemsDrawerAdaper(this@BaseActivity, generateDrawerItems(false))
    }
  }

  private fun generateDrawerItems(isClientAccount: Boolean): ArrayList<String>{
    return if (isClientAccount){
      arrayListOf("Profile", "Diet info", "Trainer chat")
    }else{
      arrayListOf("Profile", "Clients info", "Client chat")
    }
  }

  protected fun getToolbarTitle(): String{
    return if (isClientAccount()){
      getString(R.string.app_name).plus(" ").plus(getString(R.string.toolbar_client))
    }else{
      getString(R.string.app_name).plus(" ").plus(getString(R.string.toolbar_trainer))
    }
  }

  private fun isClientAccount(): Boolean{
    return this::class.java.simpleName == MainClientActivity::class.java.simpleName
  }

  override fun setContentView(layoutId: Int) {
    val child = layoutInflater.inflate(layoutId, null)
    container.addView(child)
  }

  private fun toggleDrawer() {
    if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.openDrawer(Gravity.START, true)
    } else {
      drawerLayout.closeDrawer(Gravity.END, true)
    }
  }

  private fun setOnClickListeners() {
    drawerList.onItemClickListener = OnItemClickListener { parent, view, position, id ->
//      var fragment: Fragment? = null
//
//      when (position) {
//        1, 2, 3 -> {
//          fragment = DrawerBaseFragment()
//        }
//      }
//      val fragmentManager = supportFragmentManager
//
//      fragmentManager.beginTransaction()
//              .replace(R.id.base_container, fragment!!)
//              .commit()
//
//      drawerList.setItemChecked(position, true)
//
//      val title = optionsMenu[position]
//      supportActionBar!!.title = title
//
//      drawerLayout.closeDrawer(drawerList)
    }
  }
}