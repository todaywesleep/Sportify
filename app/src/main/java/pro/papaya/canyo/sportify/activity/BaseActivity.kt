package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import pro.papaya.canyo.myapplication.R
import android.support.v4.widget.DrawerLayout
import android.view.Gravity
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.AdapterView.OnItemClickListener
import pro.papaya.canyo.sportify.adapter.DrawerItemsAdapter
import pro.papaya.canyo.sportify.fragment.DrawerBaseFragment
import pro.papaya.canyo.sportify.fragment.DrawerBaseFragment.Companion.ITEM_TYPE_TAG
import pro.papaya.canyo.sportify.fragment.DrawerBaseFragment.Companion.VIEW_TYPE_ITEM
import pro.papaya.canyo.sportify.fragment.DrawerBaseFragment.Companion.VIEW_TYPE_PROFILE


abstract class BaseActivity : AppCompatActivity() {
  private lateinit var container: FrameLayout
  private lateinit var drawerButton: ImageView
  private lateinit var toolbar: Toolbar
  private lateinit var drawerLayout: DrawerLayout
  private lateinit var drawerList: ListView

  private val optionsMenu = arrayListOf("Option 1", "Option 2", "Option 3");

  private val toolbarOnClickListener = View.OnClickListener { view ->
    when (view.id) {
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
    drawerList = findViewById(R.id.left_drawer)
    drawerList.adapter = DrawerItemsAdapter(
            this@BaseActivity,
            arrayListOf("Item 1", "Item 2", "Item 3")
    )

    drawerButton.setOnClickListener(toolbarOnClickListener)

    setOnClickListeners()
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