package pro.papaya.canyo.sportify.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import pro.papaya.canyo.myapplication.R
import pro.papaya.canyo.sportify.model.RegisterBody
import pro.papaya.canyo.sportify.utils.Utils.Companion.getRequestDateFormat
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
            fun onNextButtonPressed(pageType: Int, registerBody: RegisterBody?)
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
        var registerBody = RegisterBody()

        arguments?.takeIf { it.containsKey(ARG_PAGE_TYPE) }?.apply {
            currentPage = getInt(ARG_PAGE_TYPE)
        }

        val rootView: View = inflater.inflate(
                getLayoutId(currentPage), container, false)
        createViewHolders(currentPage, rootView)

        rootView.findViewById<Button>(R.id.register_next_step)?.setOnClickListener {
            if (mCallback != null) {
                when (currentPage) {
                    BIO_PAGE -> {
                        registerBody.fillBioInformation(
                                bioPageViewHolder.getFirstName(),
                                bioPageViewHolder.getSecondName(),
                                bioPageViewHolder.getBirthday()
                        )
                    }
                    PLACE_PAGE -> {
                        registerBody.fillLocationInformation(
                                placePageViewHolder.getCountryName(),
                                placePageViewHolder.getCityName(),
                                23.32,
                                32.23
                        )
                    }
                    CREDENTIALS_PAGE -> {
                        registerBody.fillCredentialsInformation(
                                credentialsPageViewHolder.getLogin(),
                                credentialsPageViewHolder.getPassword()
                        )
                    }
                }

                mCallback!!.onNextButtonPressed(currentPage, null)
            }
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
            else -> credentialsPageViewHolder = CredentialsPageViewHolder(rootView)
        }
    }

    fun setCallback(callback: Callback) {
        this.mCallback = callback
    }

    class BioPageViewHolder(rootView: View) : BaseRegisterPageView(rootView) {
        private val firstName: EditText = rootView.findViewById(R.id.register_bio_first_name)
        private val secondName: EditText = rootView.findViewById(R.id.register_bio_second_name)
        private val birthday: EditText = rootView.findViewById(R.id.register_bio_birthday)
        fun getFirstName(): String {
            return firstName.text.toString()
        }

        fun getSecondName(): String {
            return secondName.text.toString()
        }

        fun getBirthday(): String {
            return birthday.text.toString()
        }

        init {
            nextButton.isClickable = false
            firstName.addTextChangedListener(generateTextListener(firstName))
            secondName.addTextChangedListener(generateTextListener(secondName))
            birthday.addTextChangedListener(generateTextListener(birthday))
            birthday.run {
                isClickable = true
                isFocusableInTouchMode = false
                isFocusable
                setOnClickListener {
                    DatePickerDialog(rootView.context, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        val calendar = Calendar.getInstance()
                        calendar.set(year, monthOfYear, dayOfMonth)
                        birthday.setText(getRequestDateFormat(calendar.time))
                    },
                            Calendar.getInstance().get(Calendar.YEAR),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()
                }
            }
        }

        private fun generateTextListener(forView: EditText): TextWatcher {
            return object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.isNullOrEmpty()) forView.setBackgroundResource(R.drawable.input_incorrect)
                    else forView.setBackgroundResource(R.drawable.input_base)

                    setNextButtonState()
                }
            }
        }

        override fun setNextButtonState() {
            if (firstName.text.isEmpty()
                    || secondName.text.isEmpty()
                    || birthday.text.isEmpty()) {
                nextButton.setBackgroundResource(R.drawable.button_inactive)
                nextButton.isClickable = false
            } else {
                nextButton.setBackgroundResource(R.drawable.button_base)
                nextButton.isClickable = true
            }
        }
    }

    class PlacePageViewHolder(rootView: View) : BaseRegisterPageView(rootView) {
        private val countryName: EditText = rootView.findViewById(R.id.register_country)
        private val cityName: EditText = rootView.findViewById(R.id.register_city)
        init {
            nextButton.isClickable = false
            countryName.addTextChangedListener(generateTextListener(countryName))
            cityName.addTextChangedListener(generateTextListener(cityName))
        }

        fun getCountryName(): String {
            return countryName.text.toString()
        }

        fun getCityName(): String {
            return cityName.text.toString()
        }

        private fun generateTextListener(forView: EditText): TextWatcher {
            return object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.isNullOrEmpty()) forView.setBackgroundResource(R.drawable.input_incorrect)
                    else forView.setBackgroundResource(R.drawable.input_base)

                    setNextButtonState()
                }
            }
        }

        override fun setNextButtonState() {
            if (countryName.text.isEmpty()
                    || cityName.text.isEmpty()) {
                nextButton.setBackgroundResource(R.drawable.button_inactive)
                nextButton.isClickable = false
            } else {
                nextButton.setBackgroundResource(R.drawable.button_base)
                nextButton.isClickable = true
            }
        }
    }

    class CredentialsPageViewHolder(rootView: View) : BaseRegisterPageView(rootView) {
        private val login: EditText = rootView.findViewById(R.id.register_login)
        private val password: EditText = rootView.findViewById(R.id.register_password)
        private val repeatPassword: EditText = rootView.findViewById(R.id.register_repeat_password)
        fun getLogin(): String {
            return login.text.toString()
        }

        fun getPassword(): String {
            return password.text.toString()
        }

        init {
            nextButton.isClickable = false
            login.addTextChangedListener(generateTextListener(login))
            password.addTextChangedListener(generatePasswordChangeListener(true))
            repeatPassword.addTextChangedListener(generatePasswordChangeListener(false))
        }

        private fun generatePasswordChangeListener(isPasswordField: Boolean): TextWatcher {
            return object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val text = s ?: ""
                    val fieldToCheck = run {
                        if (isPasswordField) password
                        else repeatPassword
                    }

                    if (text.isEmpty()
                            || text != run {
                                if (isPasswordField) repeatPassword.text
                                else password.text
                            }) {
                        fieldToCheck.setBackgroundResource(R.drawable.input_incorrect)
                    } else {
                        fieldToCheck.setBackgroundResource(R.drawable.input_base)
                    }
                }
            }
        }

        private fun generateTextListener(forView: EditText): TextWatcher {
            return object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s.isNullOrEmpty()) forView.setBackgroundResource(R.drawable.input_incorrect)
                    else forView.setBackgroundResource(R.drawable.input_base)

                    setNextButtonState()
                }
            }
        }

        override fun setNextButtonState() {
            if (login.text.isEmpty()
                    || password.text.isEmpty()
                    || repeatPassword.text.isEmpty()) {
                nextButton.setBackgroundResource(R.drawable.button_inactive)
                nextButton.isClickable = false
            } else {
                nextButton.isClickable = true
                nextButton.setBackgroundResource(R.drawable.button_base)
            }
        }
    }

    abstract class BaseRegisterPageView(rootView: View) {
        protected val nextButton: Button = rootView.findViewById(R.id.register_next_step)

        abstract fun setNextButtonState()
    }
}