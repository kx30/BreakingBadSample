package ru.nikolyashka.breakingbadsample.di.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.nikolyashka.breakingbadsample.core.CharacterDataBase
import ru.nikolyashka.breakingbadsample.core.CharacterDataBase.Companion.CHARACTER_DATABASE_NAME
import ru.nikolyashka.gateway.room.CharacterDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideCharactersDatabase(@ApplicationContext context: Context): CharacterDataBase {
        return Room.databaseBuilder(context, CharacterDataBase::class.java, CHARACTER_DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharactersDao(characterDataBase: CharacterDataBase): CharacterDao {
        return characterDataBase.characterDao()
    }
}