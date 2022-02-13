package ru.nikolyashka.breakingbadsample.core

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nikolyashka.gateway.room.CharacterDao
import ru.nikolyashka.gateway.models.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class CharacterDataBase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        const val CHARACTER_DATABASE_NAME = "CharacterDataBase"
    }
}