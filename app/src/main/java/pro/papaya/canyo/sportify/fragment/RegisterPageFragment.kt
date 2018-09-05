package pro.papaya.canyo.sportify.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import pro.papaya.canyo.myapplication.R
import java.util.*

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

    private lateinit var bioPageViewHolder: BioPageViewHolder
    private lateinit var placePageViewHolder: PlacePageViewHolder
    private lateinit var credentialsPageViewHolder: CredentialsPageViewHolder

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var currentPage = -1

        arguments?.takeIf { it.containsKey(ARG_PAGE_TYPE) }?.apply {
            currentPage = getInt(ARG_PAGE_TYPE)
        }

        val rootView: View = inflater.inflate(
                getLayoutId(currentPage), container, false)
        createViewHolders(currentPage, rootView)

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

    private fun createViewHolders(position: Int, rootView: View) {
        when (position) {
            BIO_PAGE -> bioPageViewHolder = BioPageViewHolder(rootView)
            PLACE_PAGE -> placePageViewHolder = PlacePageViewHolder(rootView)
            CREDENTIALS_PAGE -> credentialsPageViewHolder = CredentialsPageViewHolder(rootView)
            else ->credentialsPageViewHolder = CredentialsPageViewHolder(rootView)
        }
    }

    fun setCallback(callback: Callback) {
        this.mCallback = callback
    }

    class BioPageViewHolder(rootView: View) {
        private val firstName: TextView = rootView.findViewById(R.id.register_bio_first_name)
        private val SecondName: TextView = rootView.findViewById(R.id.register_bio_first_name)
        private val birthday: TextView = rootView.findViewById(R.id.register_bio_first_name)
    }

    class PlacePageViewHolder(rootView: View) {
        private val countryName: TextView = rootView.findViewById(R.id.register_country)
        private val cityName: TextView = rootView.findViewById(R.id.register_city)
    }

    class CredentialsPageViewHolder(rootView: View){
        private val login: TextView = rootView.findViewById(R.id.register_login)
        private val password: TextView = rootView.findViewById(R.id.register_password)
        private val repeatPassword: TextView = rootView.findViewById(R.id.register_repeat_password)
    }
}