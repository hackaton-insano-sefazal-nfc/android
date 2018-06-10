package br.com.danielsan.notafiscalcidada.data.persistence

/**
 * Created by daniel on 19/08/17.
 */

fun Preferences.contains(key: PreferencesKey): Boolean {
    return contains(key.name)
}

fun Preferences.getString(key: PreferencesKey, defaultValue: String = ""): String {
    return getString(key.name, defaultValue)
}

fun Preferences.getInt(key: PreferencesKey, defaultValue: Int = 0): Int {
    return getInt(key.name, defaultValue)
}

fun Preferences.getLong(key: PreferencesKey, defaultValue: Long = 0): Long {
    return getLong(key.name, defaultValue)
}

fun Preferences.getFloat(key: PreferencesKey, defaultValue: Float = 0.0F): Float {
    return getFloat(key.name, defaultValue)
}

fun Preferences.getBoolean(key: PreferencesKey, defaultValue: Boolean = false): Boolean {
    return getBoolean(key.name, defaultValue)
}

fun Preferences.put(key: PreferencesKey, value: Any?) {
    put(key.name, value)
}

fun Preferences.remove(key: PreferencesKey) {
    remove(key.name)
}
