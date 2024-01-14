package com.example.test1kmm.data

import com.example.test1kmm.contacts.domain.Contact
import com.example.test1kmm.core.ImageStorage
import database.ContactEntity

suspend fun ContactEntity.toContact(imageStorage: ImageStorage): Contact {
    return Contact(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phoneNumber = phoneNumber,
        photoBytes = imagePath?.let {
            imageStorage.getImage(it)
        }
    )
}