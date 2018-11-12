package pro.papaya.canyo.sportify.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.adapter.RegisterPageAdapter
import pro.papaya.canyo.sportify.fragment.RegisterPageFragment
import pro.papaya.canyo.sportify.model.RegisterBody

class RegisterActivity :
        AppCompatActivity(),
        View.OnClickListener,
        RegisterPageFragment.Companion.Callback {
  private lateinit var mRegisterPageAdapter: RegisterPageAdapter
  private lateinit var mViewPager: ViewPager

  private lateinit var backButton: ImageButton

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.register_back_arrow -> finish()
    }
  }

  @SuppressLint("ClickableViewAccessibility")
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

  override fun onNextButtonPressed(pageType: Int, registerBody: RegisterBody?) {
    if (mViewPager.currentItem < 2) {
      mViewPager.setCurrentItem(mViewPager.currentItem + 1, true)
    } else {
      val longitude = 53.893009
      val latitude = 27.567444

//            val call = ApiClient.register(registerBody!!)

//            call.enqueue(object : Callback<BaseResponse> {
//                override fun onFailure(call: Call<BaseResponse>?, t: Throwable?) {
//                    Toast.makeText(this@RegisterActivity, "Error", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onResponse(call: Call<BaseResponse>?, response: Response<BaseResponse>?) {
//                    Toast.makeText(this@RegisterActivity, response?.body()?.status.toString(), Toast.LENGTH_SHORT).show()
//                }
//
//            })
    }
  }
}