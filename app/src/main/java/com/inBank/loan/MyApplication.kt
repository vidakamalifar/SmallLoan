package com.inBank.loan

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.inBank.loan.model.Client

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext
        pref = context?.getSharedPreferences(Keys.PREFS_NAME, Context.MODE_PRIVATE)
        editor = pref?.edit()

        createClient()
    }

    fun createClient(): List<Client> {
        return setOf(
            Client(id = 49002010965, creditModifier = 0F),
            Client(id = 49002010976, creditModifier = 100F),
            Client(id = 49002010987, creditModifier = 300F),
            Client(id = 49002010998, creditModifier = 1000F)
        ).toList()
    }

    init {
        instance = this
    }


    companion object {
        private var context: Context? = null
        private var instance: MyApplication? = null
        private var pref: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null


        fun applicationContext(): Context? {
            return instance?.applicationContext
        }

        //set client identity number
        fun setClientIdNumber(clientId: Long) {
            editor?.putLong(Keys.CLIENT_ID_NUMBER, clientId)?.apply()
        }

        //get client identity number
        fun getClientIdNumber(): Long? {
            return pref?.getLong(Keys.CLIENT_ID_NUMBER, 0)
        }
    }

    private object Keys {
        const val PREFS_NAME = "appSharedPrefName"
        const val CLIENT_ID_NUMBER = "clientIdNumber"
    }
}