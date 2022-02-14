package ru.nikolyashka.core

interface ErrorHandler {

    fun defineErrorType(e: Throwable): ErrorType
}