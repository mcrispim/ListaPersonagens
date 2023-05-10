package com.example.listapersonagens.data.repositories

interface LoginRepository {

    fun login(email: String, senha: String): Boolean

}