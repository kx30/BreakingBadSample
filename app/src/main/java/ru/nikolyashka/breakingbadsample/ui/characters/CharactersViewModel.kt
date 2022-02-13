package ru.nikolyashka.breakingbadsample.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.gateways.CharacterGateway
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterGateway: CharacterGateway,
    private val mapper: Mapper<List<CharacterUiType>, List<CharacterType>>
) : ViewModel() {

    private val _characters = MutableLiveData<List<CharacterUiType>>()
    private var currentPage = 0
    private var isLoading = false
    val characters: LiveData<List<CharacterUiType>> = _characters


    init {
        onLoadData()
    }

    fun onAddToFavorite(character: CharacterUiType.CharacterUiModel) {

    }

    fun onLoadData() {
        if (isLoading) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val characterTypes = mapper.map(characterGateway.getCharacters(currentPage))
            if (characterTypes.isNotEmpty()) {
                _characters.postValue(characterTypes)
                currentPage++
            }
            isLoading = false
        }
    }
}