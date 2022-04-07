package com.geekbrains.librariespopular

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.geekbrains.librariespopular.data.LoginUsecaseImpl
import com.geekbrains.librariespopular.data.MockLoginApiImpl
import com.geekbrains.librariespopular.domain.LoginApi
import com.geekbrains.librariespopular.domain.LoginUsecase

class App : Application() {
    private val loginApi: LoginApi by lazy { MockLoginApiImpl() }
    val loginUsecase: LoginUsecase by lazy { LoginUsecaseImpl(app.loginApi, Handler(Looper.getMainLooper())) }
}

val Context.app: App
    get() {
        return applicationContext as App
    }