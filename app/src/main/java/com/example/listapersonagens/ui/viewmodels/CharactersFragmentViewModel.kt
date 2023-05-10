package com.example.listapersonagens.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listapersonagens.data.model.Charackter
import com.example.listapersonagens.data.model.CharacterType
import com.example.listapersonagens.data.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CharactersFragmentViewModel @Inject constructor(
    private val repository: CharactersRepository
): ViewModel() {

    private val _characters = MutableLiveData<List<Charackter>>()
    val characters: LiveData<List<Charackter>>
        get() = _characters

    private val _characterType = MutableLiveData<CharacterType>()
    val characterType: LiveData<CharacterType>
        get() = _characterType

    fun setCharacterType(characterType: CharacterType) {
        _characterType.value = characterType
        viewModelScope.launch(Dispatchers.IO) {
            val charactersResult = repository.getCharacters(characterType)
            withContext(Dispatchers.Main) {
                _characters.value = charactersResult
            }
        }
    }
}