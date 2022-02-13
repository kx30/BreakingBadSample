package ru.nikolyashka.gateway.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.nikolyashka.gateway.models.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM  CharacterEntity")
    suspend fun getAll(): List<CharacterEntity>

    @Insert
    suspend fun insert(character: CharacterEntity)
}