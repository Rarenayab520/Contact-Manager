package com.nayab.contactmnager

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ContactData {
    private const val PREFS_NAME = "contacts_prefs"
    private const val KEY_LIST = "contacts_json"

    val contactList: MutableList<Contact> = mutableListOf()

    // call from Application or MainActivity.onCreate()
    fun load(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.getString(KEY_LIST, null)?.let { json ->
            val type = object : TypeToken<List<Contact>>() {}.type
            val saved: List<Contact> = Gson().fromJson(json, type)
            contactList.clear()
            contactList.addAll(saved)
        }
    }

    // call whenever you add/remove a contact
    fun save(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(contactList)
        prefs.edit { putString(KEY_LIST, json) }
    }
}

