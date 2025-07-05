package com.nayab.contactmnager

import android.net.Uri

data class FavoriteContact(
    override val firstName: String,
    override val lastName: String,
    override val phone: String,
    override val imageUri: Uri? = null
) : BaseContact(firstName, lastName, phone, imageUri) {
    override fun getContactType(): String = "Favorite"
}
