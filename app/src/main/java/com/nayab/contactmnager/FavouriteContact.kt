package com.nayab.contactmnager

class FavoriteContact(
    name: String,
    phoneNumber: String,
    email: String
) : Contact(name, phoneNumber, email) {

    override fun displayInfo(): String {
        return super.displayInfo()
    }

    override fun getContactType(): String {
        return "Favorite Contact"
    }

    // Optional - already handled by Contact
    override fun getName(): String {
        return super.getName()
    }
}
