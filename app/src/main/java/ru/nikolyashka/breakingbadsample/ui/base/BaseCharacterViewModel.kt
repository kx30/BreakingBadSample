package ru.nikolyashka.breakingbadsample.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nikolyashka.breakingbadsample.ui.characters.adapter.models.CharacterUiType
import ru.nikolyashka.core.Mapper
import ru.nikolyashka.domain.CharacterType

abstract class BaseCharacterViewModel(
    private val mapper: Mapper<List<CharacterUiType>, List<CharacterType>>,
) : BaseViewModel() {

    private var isLoading = false
    private var lastVisibleItemPosition = -1
    protected val _characters = MutableLiveData<List<CharacterUiType>>()
    val characters: LiveData<List<CharacterUiType>> = _characters


    protected abstract suspend fun getCharacters(): List<CharacterType>
    abstract fun onAddToFavorite(character: CharacterUiType.CharacterUiModel)

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
            val data = getCharacters()
            _characters.postValue(mapper.map(data))
            isLoading = false
        }
    }
}