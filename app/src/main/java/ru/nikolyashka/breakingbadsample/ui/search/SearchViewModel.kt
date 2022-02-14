package ru.nikolyashka.breakingbadsample.ui.search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterViewModel
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.usecase.FavoritesUseCase
import ru.nikolyashka.usecase.SearchUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val mapper: Mapper<List<CharacterUiType>, List<CharacterType>>,
    favoritesUseCase: FavoritesUseCase,
) : BaseCharacterViewModel(favoritesUseCase, mapper) {

    override suspend fun getCharacters(): List<CharacterType> = arrayListOf()


    fun onSearch(searchingText: String) {
        if (searchingText.length < MIN_SYMBOLS_FOR_SEARCH) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            _characters.postValue(mapper.map(searchUseCase.getCharactersBySearch(searchingText)))
        }
    }

    companion object {
        private const val MIN_SYMBOLS_FOR_SEARCH = 3
    }
}