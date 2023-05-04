package com.example.listapersonagens.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.listapersonagens.data.model.Charackter
import com.example.listapersonagens.data.model.CharacterType
import com.example.listapersonagens.data.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersFragmentViewModel() : ViewModel() {

    private val _characters = MutableLiveData<List<Charackter>>()
    val characters: LiveData<List<Charackter>>
        get() = _characters

    private val _characterType = MutableLiveData<CharacterType>()
    val characterType: LiveData<CharacterType>
        get() = _characterType

    val repository = CharactersRepository()
    fun setCharacterType(characterType: CharacterType) {
        _characterType.value = characterType
        viewModelScope.launch(Dispatchers.IO) {
            _characters.value = repository.getCharacters(characterType)
        }
    }

}