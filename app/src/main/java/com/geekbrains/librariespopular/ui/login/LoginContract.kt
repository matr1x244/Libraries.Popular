package com.geekbrains.librariespopular.ui.login

import androidx.annotation.MainThread
import com.geekbrains.librariespopular.utils.Publisher

interface LoginContract {

    interface ViewModel {
        val shouldShowProgress: Publisher<Boolean>
        val isSuccess: Publisher<Boolean>
        val errorText: Publisher<String?>
        val successText: Publisher<String?>

        @MainThread
        fun onLogin(login: String, password: String)

        @MainThread
        fun onCredentialsChanged()
    }
}