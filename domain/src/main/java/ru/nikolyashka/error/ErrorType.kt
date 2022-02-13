package ru.nikolyashka.error

sealed class ErrorType {
    object NetworkUnavailable: ErrorType()
    object Common: ErrorType()
}
