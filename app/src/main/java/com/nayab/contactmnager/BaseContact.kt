package com.nayab.contactmnager

import android.net.Uri

abstract class BaseContact(
    open val firstName: String,
    open val lastName: String,
    open val phone: String,
    open val imageUri: Uri? = null
) {
    abstract fun getContactType(): String

    fun getName(): String {
        return firstName
    }

    fun getLastName(): String {
        return lastName
    }

    fun getPhoneNumber(): String {
        return phone
    }

    fun getFirstInitial(): String {
        return firstName.firstOrNull()?.uppercase() ?: "?"
    }

    fun displayInfo(): String {
        return "$firstName $lastName"
    }
}
