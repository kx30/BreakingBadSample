package ru.nikolyashka.gateway.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.nikolyashka.gateway.models.entities.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM  CharacterEntity")
    suspend fun getAll(): List<CharacterEntity>

    @Delete
    suspend fun delete(character: CharacterEntity)

    @Insert
    suspend fun insert(character: CharacterEntity)
}