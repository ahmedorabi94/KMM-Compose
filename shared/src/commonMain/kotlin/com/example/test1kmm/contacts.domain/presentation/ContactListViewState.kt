package com.example.test1kmm.contacts.domain.presentation

import com.example.test1kmm.contacts.domain.Contact

data class ContactListViewState(
    val contacts: List<Contact> = emptyList(),
    val recentAddedContacts: List<Contact> = emptyList(),
    val selectedContact: Contact? = null,
    val isAddContactSheetOpen: Boolean = false,
    val isSelectedContactSheetOpen: Boolean = false,
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val emailError: String? = null,
    val phoneNumberError: String? = null,
)
