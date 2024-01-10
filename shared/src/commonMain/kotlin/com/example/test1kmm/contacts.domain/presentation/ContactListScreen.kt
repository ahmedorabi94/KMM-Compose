package com.example.test1kmm.contacts.domain.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.test1kmm.contacts.domain.Contact
import com.example.test1kmm.contacts.domain.presentation.components.AddContactSheet
import com.example.test1kmm.contacts.domain.presentation.components.ContactListItem
import com.example.test1kmm.core.ImagePicker


@Composable
fun ContactListScreen(
    state: ContactListViewState,
    newContact: Contact?,
    onEvent: (ContactsListEvents) -> Unit,
    imagePicker: ImagePicker
) {

    imagePicker.registerPicker { imageBytes ->
        onEvent(ContactsListEvents.OnPhotoClicked(imageBytes))
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(ContactsListEvents.OnAddNewContact)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.PersonAdd,
                    contentDescription = "Add Contact"
                )

            }
        }
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            item {
                Text(
                    text = "My Contacts ${state.contacts.size}",
                    modifier = Modifier.fillMaxWidth().padding(
                        horizontal = 16.dp
                    ),
                    fontWeight = FontWeight.Bold
                )
            }

            items(state.contacts) { contact ->
                ContactListItem(
                    contact = contact,
                    modifier = Modifier.fillMaxWidth().clickable {
                        onEvent(ContactsListEvents.SelectContact(contact))
                    }.padding(horizontal = 16.dp)
                )

            }

        }

    }


//    ContactDetailSheet(
//        isOpen = state.isSelectedContactSheetOpen,
//        selectedContact = state.selectedContact,
//        onEvent = onEvent,
//    )
    AddContactSheet(
        state = state,
        newContact = newContact,
        isOpen = state.isAddContactSheetOpen,
        onEvent = { event ->
            if(event is ContactsListEvents.OnAddPhotoClicked) {
                imagePicker.pickImage()
            }
            onEvent(event)
        },
    )

}