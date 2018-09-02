package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.activity.RegisterPage.Companion.ARG_PAGE_TYPE

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private lateinit var mDemoCollectionPagerAdapter: DemoCollectionPagerAdapter
    private lateinit var mViewPager: ViewPager

    private lateinit var backButton: ImageButton

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.register_back_arrow -> finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mViewPager = findViewById(R.id.registration_steps)
        backButton = findViewById(R.id.register_back_arrow)

        mDemoCollectionPagerAdapter = DemoCollectionPagerAdapter(supportFragmentManager)
        mViewPager.adapter = mDemoCollectionPagerAdapter

        supportActionBar?.hide()
        backButton.bringToFront()
    }
}

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
class DemoCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 3

    override fun getItem(i: Int): Fragment {
        val fragment = RegisterPage()
        fragment.arguments = Bundle().apply {
            putInt(ARG_PAGE_TYPE, i)
        }

        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return "OBJECT " + (position + 1)
    }
}

// Instances of this class are fragments representing a single
// object in our collection.
class RegisterPage : Fragment() {
    companion object {
        const val BIO_PAGE: Int = 0
        const val PLACE_PAGE: Int = 1
        const val CREDENTIALS_PAGE: Int = 2

        const val ARG_PAGE_TYPE = "arg_page_type"
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var currentPage = -1

        arguments?.takeIf { it.containsKey(ARG_PAGE_TYPE) }?.apply {
            currentPage = getInt(ARG_PAGE_TYPE)
        }

        val rootView: View = inflater.inflate(
                getLayoutId(currentPage), container, false)

        return rootView
    }

    private fun getLayoutId(position: Int): Int {
        return when (position) {
            BIO_PAGE -> R.layout.activity_register_bio_page
            PLACE_PAGE -> R.layout.activity_register_bio_page
            CREDENTIALS_PAGE -> R.layout.activity_register_bio_page
            else -> R.layout.activity_register_bio_page
        }
    }
}