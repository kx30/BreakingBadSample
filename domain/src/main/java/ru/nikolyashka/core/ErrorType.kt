package ru.nikolyashka.core

sealed class ErrorType {
    object NetworkUnavailable : ErrorType()
    object Unknown : ErrorType()
}