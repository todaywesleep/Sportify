package pro.papaya.canyo.sportify.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import pro.papaya.canyo.sportify.fragment.RegisterPageFragment
import pro.papaya.canyo.sportify.fragment.RegisterPageFragment.Companion.ARG_PAGE_TYPE

class RegisterPageAdapter(
        fm: FragmentManager,
        private val callback: RegisterPageFragment.Companion.Callback) : FragmentStatePagerAdapter(fm) {
  override fun getCount(): Int = 3

  override fun getItem(i: Int): Fragment {
    val fragment = RegisterPageFragment()
    fragment.arguments = Bundle().apply {
      putInt(ARG_PAGE_TYPE, i)
    }
    fragment.setCallback(callback)

    return fragment
  }
}