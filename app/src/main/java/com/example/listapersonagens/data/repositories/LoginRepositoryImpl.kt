package com.example.listapersonagens.data.repositories

import com.example.listapersonagens.data.remote.authentication.Authenticator

class LoginRepositoryImpl(
    val backendAuthenticator: Authenticator,
    val firebaseAuthenticator: Authenticator
) : LoginRepository {

    override fun login(email: String, senha: String): Boolean {
        if (backendAuthenticator.login(email, senha))
            return true
        else
            return firebaseAuthenticator.login(email, senha)
    }
}