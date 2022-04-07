package com.geekbrains.librariespopular.data.userrepository

import com.geekbrains.librariespopular.domain.UserRepo
import com.geekbrains.librariespopular.domain.entities.UserProfile

class RetrofitUserRepoImpl : UserRepo {
    override fun addUser(user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): List<UserProfile> {
        TODO("Not yet implemented")
    }

    override fun changeUser(id: String, user: UserProfile) {
        TODO("Not yet implemented")
    }

    override fun deleteUser(id: String) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }
}