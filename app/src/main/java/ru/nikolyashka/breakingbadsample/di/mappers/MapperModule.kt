package ru.nikolyashka.breakingbadsample.di.mappers

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharactersUiMapper
import ru.nikolyashka.breakingbadsample.ui.details.models.CharacterDetailsUiMapper
import ru.nikolyashka.breakingbadsample.ui.details.models.CharacterDetailsUiType
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterDetailsMapper
import ru.nikolyashka.domain.CharacterType

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindCharactersUiMapper(mapper: CharactersUiMapper): Mapper<List<CharacterUiType>, List<CharacterType>>

    @Binds
    abstract fun bindCharacterDetailsMapper(mapper: CharacterDetailsUiMapper): CharacterDetailsMapper<CharacterDetailsUiType>
}