package pro.papaya.canyo.sportify.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.annotation.SuppressLint

class SharedPrefsUtils{
  companion object {
    private lateinit var prefs: SharedPreferences

    private const val KEY_PREFS_KEY = "com.sportify"
    const val KEY_ACCOUNT_TYPE = "key_account_type"
    const val KEY_USERNAME = "key_username"

    @SuppressLint("CommitPrefEdits")
    fun clearDB() {
      val editor = prefs.edit()
      editor.clear()
      editor.apply()
    }

    fun initPrefs(context: Context){
      prefs = context.getSharedPreferences(KEY_PREFS_KEY, MODE_PRIVATE)
    }

    fun getStringProperty(key: String): String?{
      return prefs.getString(key, null)
    }

    fun getBooleanProperty(key: String): Boolean{
      return prefs.getBoolean(key, false)
    }

    @SuppressLint("CommitPrefEdits")
    fun writeBoolean(key: String, value: Boolean){
      val editor = prefs.edit()
      editor.putBoolean(key, value)
      editor.apply()
    }

    @SuppressLint("CommitPrefEdits")
    fun writeString(key: String, value: String){
      val editor = prefs.edit()
      editor.putString(key, value)
      editor.apply()
    }
  }
}