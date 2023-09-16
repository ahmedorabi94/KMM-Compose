package com.example.test1kmm.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.test1kmm.ui.DarkColorScheme
import com.example.test1kmm.ui.LightColorScheme
import com.example.test1kmm.ui.Typography

@Composable
actual fun ContactsTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}