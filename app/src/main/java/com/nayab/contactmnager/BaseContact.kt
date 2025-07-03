package com.nayab.contactmnager

abstract class BaseContact {
    abstract fun getContactType(): String
    abstract fun displayInfo(): String
    abstract fun getName(): String

}
