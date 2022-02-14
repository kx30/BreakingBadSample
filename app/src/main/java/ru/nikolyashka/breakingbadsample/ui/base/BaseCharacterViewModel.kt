package ru.nikolyashka.breakingbadsample.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType
import ru.nikolyashka.usecase.FavoritesUseCase

abstract class BaseCharacterViewModel(
    private val favoritesUseCase: FavoritesUseCase,
    private val mapper: Mapper<List<CharacterUiType>, List<CharacterType>>,
) : BaseViewModel() {

    private var isLoading = false
    private var lastVisibleItemPosition = -1
    protected val _characters = MutableLiveData<List<CharacterUiType>>()
    val characters: LiveData<List<CharacterUiType>> = _characters


    protected open suspend fun getCharacters(): List<CharacterType> = arrayListOf()

    fun onAddToFavorite(character: CharacterUiType.CharacterUiModel) {
        _characters.value = _characters.value?.map {
            if (it is CharacterUiType.CharacterUiModel) {
                if (it.id == character.id) {
                    viewModelScope.launch(Dispatchers.IO) {
                        favoritesUseCase.changeFavoriteCharacterState(it.map())
                    }
                    return@map it.copy(isFavorite = !it.isFavorite)
                }
            }
            it
        }
    }

    fun onLoadMoreData(lastVisibleItemPosition: Int) {
        if (lastVisibleItemPosition != this.lastVisibleItemPosition) {
            onLoadData()
            this.lastVisibleItemPosition = lastVisibleItemPosition
        }
    }

    fun onLoadData() {
        if (isLoading) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            _characters.postValue(mapper.map(getCharacters()))
            isLoading = false
        }
    }
}