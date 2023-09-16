package com.example.test1kmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform