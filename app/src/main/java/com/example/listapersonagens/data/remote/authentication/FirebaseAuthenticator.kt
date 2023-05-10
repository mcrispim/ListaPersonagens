package com.example.listapersonagens.data.remote.authentication

import com.example.listapersonagens.data.model.User

object FirebaseAuthenticator : Authenticator {
    
    private val registeredUsers = listOf(
        User("teste1@gmail.com", "123"),
        User("teste2@gmail.com", "456")
    )
    
    override fun login(email: String, password: String) =
        registeredUsers.any { it.email == email && it.password == password }

}