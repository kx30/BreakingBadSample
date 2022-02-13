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
import ru.nikolyashka.usecase.CharacterUseCase
import ru.nikolyashka.usecase.FavoritesUseCase
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase,
    private val favoriteUseCase: FavoritesUseCase,
    private val mapperFromModelToUiModel: Mapper<List<CharacterUiType>, List<CharacterType>>,
) : ViewModel() {

    private val _characters = MutableLiveData<List<CharacterUiType>>()
    private var isLoading = false
    val characters: LiveData<List<CharacterUiType>> = _characters


    init {
        _characters.value = mapperFromModelToUiModel.map(characterUseCase.getInitialData())
    }

    fun onAddToFavorite(character: CharacterUiType.CharacterUiModel) {


        // Todo: вынести в юзкейс
        _characters.value = _characters.value?.map {
            if (it is CharacterUiType.CharacterUiModel) {
                if (it.id == character.id) {
                    viewModelScope.launch {
                        favoriteUseCase.changeFavoriteCharacter(it.map(it)) // Todo: вомзожно пересмотреть это
                    }
                    return@map it.copy(isFavorite = !it.isFavorite)
                }
            }
            it
        }
    }

    fun onLoadData() {
        if (isLoading) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            val characterTypes = mapperFromModelToUiModel.map(characterUseCase.getCharacters())
            _characters.postValue(characterTypes)
            isLoading = false
        }
    }
}