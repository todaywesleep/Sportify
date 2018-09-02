package pro.papaya.canyo.sportify.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import pro.papaya.canyo.myapplication.R

// Instances of this class are fragments representing a single
// object in our collection.
class RegisterPageFragment : Fragment() {
    companion object {
        const val BIO_PAGE: Int = 0
        const val PLACE_PAGE: Int = 1
        const val CREDENTIALS_PAGE: Int = 2

        const val ARG_PAGE_TYPE = "arg_page_type"

        interface Callback {
            fun onNextButtonPressed(pageType: Int)
        }
    }

    private var mCallback: Callback? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var currentPage = -1

        arguments?.takeIf { it.containsKey(ARG_PAGE_TYPE) }?.apply {
            currentPage = getInt(ARG_PAGE_TYPE)
        }

        val rootView: View = inflater.inflate(
                getLayoutId(currentPage), container, false)
        rootView.findViewById<Button>(R.id.register_next_step)?.setOnClickListener {
            if (mCallback != null) mCallback!!.onNextButtonPressed(currentPage)
        }

        return rootView
    }

    private fun getLayoutId(position: Int): Int {
        return when (position) {
            BIO_PAGE -> R.layout.activity_register_bio_page
            PLACE_PAGE -> R.layout.activity_register_place_page
            CREDENTIALS_PAGE -> R.layout.activity_register_credentials_page
            else -> R.layout.activity_register_bio_page
        }
    }

    fun setCallback(callback: Callback) {
        this.mCallback = callback
    }
}