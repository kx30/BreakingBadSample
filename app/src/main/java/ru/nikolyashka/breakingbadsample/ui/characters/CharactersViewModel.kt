package ru.nikolyashka.breakingbadsample.ui.characters

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterViewModel
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.usecase.CharacterUseCase
import ru.nikolyashka.usecase.FavoritesUseCase
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase,
    private val mapper: Mapper<List<CharacterUiType>, List<CharacterType>>,
    favoriteUseCase: FavoritesUseCase,
) : BaseCharacterViewModel(favoriteUseCase, mapper) {

    override suspend fun getCharacters(): List<CharacterType> = characterUseCase.getCharacters()

    override fun onResume() {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.postValue(mapper.map(characterUseCase.getInitialCharacters()))
        }
    }
}