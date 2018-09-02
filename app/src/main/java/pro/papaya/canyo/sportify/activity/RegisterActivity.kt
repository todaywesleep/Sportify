package pro.papaya.canyo.sportify.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageButton
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.RegisterPageAdapter
import pro.papaya.canyo.sportify.fragment.RegisterPageFragment

class RegisterActivity : AppCompatActivity(), View.OnClickListener, RegisterPageFragment.Companion.Callback {
    private lateinit var mRegisterPageAdapter: RegisterPageAdapter
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

        mRegisterPageAdapter = RegisterPageAdapter(supportFragmentManager, this)
        mViewPager.adapter = mRegisterPageAdapter

        supportActionBar?.hide()
        backButton.setOnClickListener(this)
        backButton.bringToFront()
    }

    override fun onNextButtonPressed(pageType: Int) {
        if (mViewPager.currentItem < 2)
            mViewPager.setCurrentItem(mViewPager.currentItem + 1, true)
    }
}