package com.nayab.contactmnager

import android.net.Uri

abstract class BaseContact(
    @Transient open val firstName: String,
    @Transient open val lastName: String,
    @Transient open val phone: String,
    @Transient open val imageUri: Uri? = null
) {
    abstract fun getContactType(): String

    // ✅ Removed conflicting getLastName() — use property instead

    fun getName(): String {
        return firstName
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
