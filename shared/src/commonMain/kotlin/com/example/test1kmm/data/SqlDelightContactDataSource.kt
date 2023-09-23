package com.example.test1kmm.data

import com.example.test1kmm.contacts.domain.Contact
import com.example.test1kmm.contacts.domain.ContactDataSource
import com.example.test1kmm.database.ContactDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock

class SqlDelightContactDataSource(
    db: ContactDatabase
) : ContactDataSource {

    private val queries = db.contactQueries

    override fun getContacts(): Flow<List<Contact>> {
        return queries
            .getContacts()
            .asFlow()
            .mapToList()
            .map { contactEntities ->
                supervisorScope {
                    contactEntities
                        .map {
                            async { it.toContact() }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getRecentContacts(amount: Int): Flow<List<Contact>> {
        return queries
            .getRecentContacts(amount.toLong())
            .asFlow()
            .mapToList()
            .map { contactEntities ->
                supervisorScope {
                    contactEntities
                        .map {
                            async { it.toContact() }
                        }
                        .map { it.await() }
                }
            }
    }

    override suspend fun insertContact(contact: Contact) {
//        val imagePath = contact.photoBytes?.let {
//          //  imageStorage.saveImage(it)
//        }
        queries.insertContactEntity(
            id = contact.id,
            firstName = contact.firstName,
            lastName = contact.lastName,
            phoneNumber = contact.phoneNumber,
            email = contact.email,
            createdAt = Clock.System.now().toEpochMilliseconds(),
            imagePath = null
        )
    }

    override suspend fun deleteContact(id: Long) {
//        val entity = queries.getContactById(id).executeAsOne()
//        entity.imagePath?.let {
//          //  imageStorage.deleteImage(it)
//        }
        queries.deleteContact(id)
    }
}