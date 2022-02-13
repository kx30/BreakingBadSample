package ru.nikolyashka.breakingbadsample.ui.characters.adapter.models

import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import javax.inject.Inject

class CharactersUiMapper @Inject constructor() :
    Mapper<List<@JvmSuppressWildcards CharacterUiType>, List<@JvmSuppressWildcards CharacterType>> {

    private val commonMapper = CharacterUiMapper(CharacterUiViewType.CHARACTER_UI_TYPE)
    private val fullScreenErrorMapper = CharacterUiMapper(CharacterUiViewType.CHARACTER_FULL_SCREEN_ERROR)
    private val snackBarErrorMapper = CharacterUiMapper(CharacterUiViewType.CHARACTER_SNACKBAR_ERROR)


    override fun map(source: List<CharacterType>): List<CharacterUiType> {
        val result = ArrayList<CharacterUiType>()
        result.addAll(source.map {
            commonMapper.map(it as CharacterType.CharacterModel)
        })
//        when {
//            source.isEmpty() -> result.add(CharacterUiType.CharacterUiCenterLoader)
//            source.last() is CharacterType.CharacterModel -> {
//                result.addAll(
//                    source.map {
//                        it.map(commonMapper)
//                    }
//                )
//                result.add(CharacterUiType.CharacterUiBottomLoader)
//            }
//            else ->result.addAll(source.map {
//                commonMapper.map(it as CharacterType.CharacterModel)
//            })
//        }
        return result
    }
}