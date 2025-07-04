package com.nayab.contactmnager

class FavoriteContact(
    firstName: String,
    lastName: String,
    phoneNumber: String
) : Contact(firstName, lastName, phoneNumber) {

    override fun getContactType(): String {
        return "Favorite"
    }
}
