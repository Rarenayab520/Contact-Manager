package com.nayab.contactmnager

class FavoriteContact(
    name: String,
    phoneNumber: String,
    email: String
) : Contact(name, phoneNumber, email) {

    override fun displayInfo(): String {
        return super.displayInfo() + "\n⭐ Favorite"
    }

    override fun getContactType(): String {
        return "Favorite Contact"
    }
}
