package com.example.test1kmm.di

import android.content.Context
import com.example.test1kmm.contacts.domain.ContactDataSource
import com.example.test1kmm.core.DatabaseDriverFactory
import com.example.test1kmm.data.SqlDelightContactDataSource
import com.example.test1kmm.database.ContactDatabase

actual class AppModule(
    private val context: Context
) {

    actual val contactDataSource: ContactDataSource by lazy {
        SqlDelightContactDataSource(
            db = ContactDatabase(
                driver = DatabaseDriverFactory(context).create(),
            )
        )
    }
}