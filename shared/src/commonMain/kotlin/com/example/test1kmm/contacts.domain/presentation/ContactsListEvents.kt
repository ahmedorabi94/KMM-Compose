package com.example.test1kmm.contacts.domain.presentation

import com.example.test1kmm.contacts.domain.Contact

sealed interface ContactsListEvents {
    object OnAddNewContact : ContactsListEvents
    object DismissContact : ContactsListEvents
    object SaveContact : ContactsListEvents
    data class SelectContact(val contact: Contact) : ContactsListEvents

    data class EditContact(val contact: Contact) : ContactsListEvents
    object DeleteContact : ContactsListEvents

    object OnAddPhotoClicked : ContactsListEvents

    class OnPhotoClicked(val byteArray: ByteArray) : ContactsListEvents

    data class OnFirstNameChanged(val value: String) : ContactsListEvents
    data class OnLastNameChanged(val value: String) : ContactsListEvents
    data class OnEmailChanged(val value: String) : ContactsListEvents
    data class OnPhotoNumberChanged(val value: String) : ContactsListEvents


}