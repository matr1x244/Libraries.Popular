package com.geekbrains.librariespopular.data.userrepository

import com.geekbrains.librariespopular.domain.UserRepo
import com.geekbrains.librariespopular.domain.entities.UserProfile

class CombineUserRepoImpl(
    private val localRepo: UserRepo,
    private val remoteRepo: UserRepo
) : UserRepo {
    override fun addUser(user: UserProfile) {
        localRepo.addUser(user)
        remoteRepo.addUser(user)
    }

    override fun getAllUsers(): List<UserProfile> {
        localRepo.getAllUsers()
        return localRepo.getAllUsers()
    }

    override fun changeUser(id: String, user: UserProfile) {
        //..
    }

    override fun deleteUser(id: String) {
    }

    override fun deleteAll() {
        localRepo.deleteAll()
        remoteRepo.deleteAll()
    }
}