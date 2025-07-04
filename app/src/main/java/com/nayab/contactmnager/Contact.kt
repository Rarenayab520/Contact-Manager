package com.nayab.contactmnager

open class Contact(
    private var firstName: String,
    private var lastName: String,
    private var phoneNumber: String
) : BaseContact() {

    override fun getName(): String = "$firstName $lastName"

    override fun getPhoneNumber(): String = phoneNumber

    override fun displayInfo(): String {
        // Show only name in list
        return "$firstName $lastName"
    }

    override fun getContactType(): String {
        return "Regular"
    }

    override fun getFirstInitial(): String {
        return firstName.firstOrNull()?.uppercase() ?: "#"
    }

    // Optional setters
    fun setFirstName(newName: String) {
        firstName = newName
    }

    fun setLastName(newLastName: String) {
        lastName = newLastName
    }

    fun setPhoneNumber(newNumber: String) {
        phoneNumber = newNumber
    }
}
