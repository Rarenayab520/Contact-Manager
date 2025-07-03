package com.nayab.contactmnager

open class Contact(
    private var name: String,
    private var phoneNumber: String,
    private var email: String
) : BaseContact() {

    fun getName() = name
    fun getPhoneNumber() = phoneNumber
    fun getEmail() = email

    fun setName(newName: String) {
        name = newName
    }

    fun setPhoneNumber(newNumber: String) {
        phoneNumber = newNumber
    }

    fun setEmail(newEmail: String) {
        email = newEmail
    }

    override fun displayInfo(): String {
        return "Name: $name\nPhone: $phoneNumber\nEmail: $email"
    }

    override fun getContactType(): String {
        return "Regular Contact"
    }
}
