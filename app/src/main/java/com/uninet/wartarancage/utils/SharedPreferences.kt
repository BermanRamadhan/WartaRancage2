package com.uninet.wartarancage.utils

import android.content.Context
import android.preference.PreferenceManager

class SharedPreferences(val context: Context) {
    companion object{
        private const val FIRST_INSTALL = "FIRST INSTALL"
    }
    private val pm = PreferenceManager.getDefaultSharedPreferences(context)

    var isLogin = pm.getBoolean(FIRST_INSTALL,false)
    set(value) = pm.edit().putBoolean(FIRST_INSTALL,value).apply()

}
