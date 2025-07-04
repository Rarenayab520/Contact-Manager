package com.nayab.contactmnager

abstract class BaseContact {
    abstract fun getContactType(): String
    abstract fun displayInfo(): String // Used for list preview (name only)
    abstract fun getName(): String     // Full name for sorting or display
    abstract fun getPhoneNumber(): String
    abstract fun getFirstInitial(): String
}
