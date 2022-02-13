package ru.nikolyashka.core

interface Mapper<Result, Source> {

    fun map(source: Source): Result
}