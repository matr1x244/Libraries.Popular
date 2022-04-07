package com.geekbrains.librariespopular.domain

import com.geekbrains.librariespopular.domain.entities.UserProfile

interface UserRepo {
    // Create
    fun addUser(user: UserProfile)

    // Read
    fun getAllUsers(): List<UserProfile>

    // Update
    fun changeUser(id: String, user: UserProfile)

    // Delete
    fun deleteUser(id: String)
    fun deleteAll()
}