package pro.papaya.canyo.sportify.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import pro.papaya.canyo.sportify.fragment.RegisterPageFragment
import pro.papaya.canyo.sportify.fragment.RegisterPageFragment.Companion.ARG_PAGE_TYPE

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
class RegisterPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 3

    override fun getItem(i: Int): Fragment {
        val fragment = RegisterPageFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_PAGE_TYPE, i)
        }

        return fragment
    }
}