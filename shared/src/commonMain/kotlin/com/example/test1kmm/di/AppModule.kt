package com.example.test1kmm.di

import com.example.test1kmm.contacts.domain.ContactDataSource

expect class AppModule {
    val contactDataSource: ContactDataSource
}