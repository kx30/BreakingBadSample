package ru.nikolyashka.breakingbadsample.ui.favorites

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterViewModel
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.usecase.FavoritesUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteUseCase: FavoritesUseCase,
    mapper: Mapper<List<CharacterUiType>, List<CharacterType>>,
) : BaseCharacterViewModel(mapper) {

    init {
        viewModelScope.launch {
            _characters.value = mapper.map(getData())
        }
    }

    override suspend fun getData(): List<CharacterType> = favoriteUseCase.getFavoriteCharacters()

    override fun onAddToFavorite(character: CharacterUiType.CharacterUiModel) {
        _characters.value = _characters.value?.map {
            if (it is CharacterUiType.CharacterUiModel) {
                if (it.id == character.id) {
                    viewModelScope.launch {
                        favoriteUseCase.changeFavoriteCharacterState(it.map(it)) // Todo: вомзожно пересмотреть это
                    }
                    return@map it.copy(isFavorite = !it.isFavorite)
                }
            }
            it
        }
    }
}