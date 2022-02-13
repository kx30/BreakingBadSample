package ru.nikolyashka.breakingbadsample.di.mapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharactersUiMapper
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    // Todo: заменить на абстракцию
    @Binds
    abstract fun bindCharactersUiMapper(mapper: CharactersUiMapper): Mapper<List<CharacterUiType>, List<CharacterType>>
}