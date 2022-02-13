package ru.nikolyashka.gateway

import retrofit2.http.GET
import retrofit2.http.Query
import ru.nikolyashka.response.CharacterResponse

interface Api {

    @GET("/api/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = DEFAULT_LIMIT
    ): List<CharacterResponse>

    companion object {
        const val DEFAULT_OFFSET = 10
        private const val DEFAULT_LIMIT = 10
    }
}