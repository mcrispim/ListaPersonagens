package com.example.listapersonagens.data.network.authentication

import com.example.listapersonagens.data.model.User

object FirebaseAuthenticator {
    
    private val registeredUsers = listOf(
        User("teste1@gmail.com", "123"),
        User("teste2@gmail.com", "456")
    )
    
    fun login(email: String, password: String): Boolean =
        registeredUsers.any { it.email == email && it.password == password }

}