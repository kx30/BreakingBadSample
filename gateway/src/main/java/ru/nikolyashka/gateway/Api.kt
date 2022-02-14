package ru.nikolyashka.gateway

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.nikolyashka.gateway.models.responses.CharacterDetailsResponse
import ru.nikolyashka.gateway.models.responses.CharacterResponse

interface Api {

    @GET("/api/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = DEFAULT_LIMIT
    ): List<CharacterResponse>

    @GET("/api/characters")
    suspend fun getCharactersBySearch(
        @Query("name") name: String
    ): List<CharacterResponse>

    /**
     * Api send array when get by id, lol
     * */
    @GET("/api/characters/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): List<CharacterDetailsResponse>

    companion object {
        const val DEFAULT_OFFSET = 10
        private const val DEFAULT_LIMIT = 10
    }
}