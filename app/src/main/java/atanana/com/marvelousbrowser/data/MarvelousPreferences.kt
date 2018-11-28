package atanana.com.marvelousbrowser.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class MarvelousPreferences(context: Context) {
    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    private fun edit(block: SharedPreferences.Editor.() -> Unit) {
        val editor = preferences.edit()
        editor.block()
        editor.apply()
    }

    var totalCharacters: Int
        get() = preferences.getInt(KEY_TOTAL_CHARACTERS, 0)
        set(value) = edit { putInt(KEY_TOTAL_CHARACTERS, value) }

    companion object {
        const val KEY_TOTAL_CHARACTERS = "total_characters"
    }
}