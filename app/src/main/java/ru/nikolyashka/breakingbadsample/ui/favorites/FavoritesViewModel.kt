package ru.nikolyashka.breakingbadsample.ui.favorites

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterViewModel
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.usecase.FavoritesUseCase
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesUseCase: FavoritesUseCase,
    private val mapper: Mapper<List<CharacterUiType>, List<CharacterType>>
) : BaseCharacterViewModel(favoritesUseCase, mapper) {

    override fun onResume() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.postValue(mapper.map(getCharacters()))
        }
    }

    override suspend fun getCharacters(): List<CharacterType> = favoritesUseCase.getFavoriteCharacters()
}