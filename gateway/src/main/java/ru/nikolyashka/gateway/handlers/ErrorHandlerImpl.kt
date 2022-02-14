package ru.nikolyashka.gateway.handlers

import ru.nikolyashka.core.ErrorHandler
import ru.nikolyashka.core.ErrorType
import java.io.InterruptedIOException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {

    override fun defineErrorType(e: Throwable) = when (e) {
        is InterruptedIOException, is UnknownHostException -> ErrorType.NetworkUnavailable
        else -> ErrorType.Unknown
    }
}