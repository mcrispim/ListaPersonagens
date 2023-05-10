package com.example.listapersonagens.data.remote.authentication

interface Authenticator {

    fun login(email: String, password: String): Boolean

}