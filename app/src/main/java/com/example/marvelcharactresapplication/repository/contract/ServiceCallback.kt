package com.example.marvelcharactresapplication.repository.contract

interface ServiceCallback<T> {
    fun callback(response: T)
}