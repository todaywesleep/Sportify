package pro.papaya.canyo.sportify.view

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import pro.papaya.canyo.myapplication.R

class DrawerProfile(context: Context, content: String) : View(context) {
  val mContext: Context = context
  val drawerProfile: View
  val userName: TextView

  init {
    drawerProfile = (context as Activity).layoutInflater.inflate(R.layout.item_drawer_profile, null)
    userName = drawerProfile.findViewById(R.id.drawer_profile_name)
    userName.text = content
  }
}