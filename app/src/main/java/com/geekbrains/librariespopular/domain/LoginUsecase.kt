package com.geekbrains.librariespopular.domain

import androidx.annotation.MainThread

interface LoginUsecase {
    fun login(
        login: String,
        password: String,
        @MainThread callback: (Boolean) -> Unit
    )
}