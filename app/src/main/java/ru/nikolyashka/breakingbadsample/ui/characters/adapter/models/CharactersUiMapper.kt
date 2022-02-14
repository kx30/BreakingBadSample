package ru.nikolyashka.breakingbadsample.ui.characters.adapter.models

import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import javax.inject.Inject

class CharactersUiMapper @Inject constructor() :
    Mapper<List<@JvmSuppressWildcards CharacterUiType>, List<@JvmSuppressWildcards CharacterType>> {

    private val modelMapper = CharacterUiMapper(CharacterUiViewType.CHARACTER_UI_TYPE)
    private val fullScreenErrorMapper = CharacterUiMapper(CharacterUiViewType.CHARACTER_FULL_SCREEN_ERROR)
    private val bottomErrorMapper = CharacterUiMapper(CharacterUiViewType.CHARACTER_BOTTOM_ERROR)
    private val emptyDataMapper = CharacterUiMapper(CharacterUiViewType.CHARACTER_EMPTY_DATA)


    override fun map(source: List<CharacterType>): List<CharacterUiType> {
        val result = ArrayList<CharacterUiType>()
        when {
            source.isEmpty() -> result.add(CharacterUiType.CharacterUiCenterLoader)
            source.size == 1 && source[0] is CharacterType.CharacterEmptyData ->
                result.add(source[0].map(emptyDataMapper))
            source.size == 1 && source[0] is CharacterType.CharacterErrorModel ->
                result.add(source[0].map(fullScreenErrorMapper))
            source.last() is CharacterType.CharacterModel -> {
                result.addAll(
                    source.map {
                        it.map(modelMapper)
                    }
                )
            }
            source.last() is CharacterType.CharacterErrorModel -> {
                for (item in source) {
                    if (item is CharacterType.CharacterModel)
                        result.add(item.map(modelMapper))
                }
                result.add(source.last().map(bottomErrorMapper))
            }
        }
        return result
    }
}