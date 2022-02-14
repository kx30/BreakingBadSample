package ru.nikolyashka.breakingbadsample.di.handlers

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikolyashka.core.ErrorHandler
import ru.nikolyashka.gateway.handlers.ErrorHandlerImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlerModule {

    @Binds
    abstract fun bindErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler
}