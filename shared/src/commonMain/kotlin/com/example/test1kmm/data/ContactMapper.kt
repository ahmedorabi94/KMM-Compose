package com.example.test1kmm.data

import com.example.test1kmm.contacts.domain.Contact
import database.ContactEntity

suspend fun ContactEntity.toContact(): Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = null
    )
}