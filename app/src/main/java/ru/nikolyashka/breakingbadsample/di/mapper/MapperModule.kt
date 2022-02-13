package ru.nikolyashka.breakingbadsample.di.mapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharactersUiMapper
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateway.mapper.CharacterResponseMapper
import ru.nikolyashka.gateway.models.entities.CharacterEntity
import ru.nikolyashka.gateway.models.mappers.CharacterEntityToModel
import ru.nikolyashka.gateway.models.mappers.CharacterModelToEntity
import ru.nikolyashka.gateway.models.responses.CharacterResponse

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindCharactersUiMapper(mapper: CharactersUiMapper): Mapper<List<CharacterUiType>, List<CharacterType>>

    @Binds
    abstract fun bindCharacterResponseMapper(mapper: CharacterResponseMapper): Mapper<List<CharacterType>, List<CharacterResponse>>

    @Binds
    abstract fun bindCharacterModelToEntity(mapper: CharacterModelToEntity): Mapper<CharacterEntity, CharacterType.CharacterModel>

    @Binds
    abstract fun bindCharacterEntityToModel(mapper: CharacterEntityToModel): Mapper<CharacterType.CharacterModel, CharacterEntity>
}