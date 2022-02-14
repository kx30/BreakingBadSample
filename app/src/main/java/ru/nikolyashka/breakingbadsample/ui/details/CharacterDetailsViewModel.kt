package ru.nikolyashka.breakingbadsample.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.nikolyashka.breakingbadsample.ui.base.BaseViewModel
import ru.nikolyashka.breakingbadsample.ui.details.models.CharacterDetailsUiType
import ru.nikolyashka.domain.CharacterDetailsMapper
import ru.nikolyashka.gateways.CharacterDetailsGateway
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val characterDetailsGateway: CharacterDetailsGateway,
    private val mapper: CharacterDetailsMapper<CharacterDetailsUiType>
) : BaseViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    private val _characterDetails = MutableLiveData<CharacterDetailsUiType>()
    val characterDetails: LiveData<CharacterDetailsUiType> = _characterDetails
    val isLoading: LiveData<Boolean> = _isLoading


    fun loadCharacterDetails(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            _characterDetails.postValue(characterDetailsGateway.getCharacterById(characterId).map(mapper))
            _isLoading.postValue(false)
        }
    }
}