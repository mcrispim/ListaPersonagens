package com.example.listapersonagens.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listapersonagens.data.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor (private val repository: LoginRepository) : ViewModel() {

    private val _isLogged = MutableLiveData(false)
    val isLogged: LiveData<Boolean>
        get() = _isLogged

    private val _tries = MutableLiveData(0)
    val tries: LiveData<Int>
        get() = _tries

    fun setIsLogged(email: String, password: String) {
        if (repository.login(email, password))
            _isLogged.value = true
        _tries.value = _tries.value?.plus(1)
    }
}