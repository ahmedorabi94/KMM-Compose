package com.example.test1kmm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.test1kmm.contacts.domain.Contact
import com.example.test1kmm.contacts.domain.presentation.ContactListViewState
import com.example.test1kmm.contacts.domain.presentation.ContactsListEvents
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ContactListViewModel : ViewModel() {


    private val _state = MutableStateFlow(ContactListViewState(dummyList))

    val state = _state

    var newContact: Contact? by mutableStateOf(null)
        private set


    fun onEvent(events: ContactsListEvents){

    }


}

private val dummyList = (1..5).map {
    Contact(
        id = it.toLong(),
        firstName = "First $it",
        lastName = "Last $it",
        email = "test@test$it.com",
        phoneNumber = "123456789",
        photoBytes = null
    )
}