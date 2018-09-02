package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.RegisterPageAdapter

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mDemoCollectionPagerAdapter: RegisterPageAdapter
    private lateinit var mViewPager: ViewPager

    private lateinit var backButton: ImageButton

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.register_back_arrow -> finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mViewPager = findViewById(R.id.registration_steps)
        backButton = findViewById(R.id.register_back_arrow)

        mDemoCollectionPagerAdapter = RegisterPageAdapter(supportFragmentManager)
        mViewPager.adapter = mDemoCollectionPagerAdapter

        supportActionBar?.hide()
        backButton.bringToFront()
    }
}