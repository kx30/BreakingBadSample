package ru.nikolyashka.breakingbadsample.ui.search

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nikolyashka.breakingbadsample.ui.base.BaseCharacterViewModel
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateways.CharacterGateway
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val characterGateway: CharacterGateway,
    private val mapper: Mapper<List<CharacterUiType>, List<CharacterType>>,
) : BaseCharacterViewModel(mapper) {

    override suspend fun getCharacters(): List<CharacterType> {
        return arrayListOf()
    }

    override fun onAddToFavorite(character: CharacterUiType.CharacterUiModel) {

    }

    fun onSearch(searchingText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _characters.postValue(mapper.map(characterGateway.getCharactersBySearch(searchingText)))
        }
    }
}