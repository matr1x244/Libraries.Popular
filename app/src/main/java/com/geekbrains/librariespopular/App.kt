package com.geekbrains.librariespopular

import android.app.Application
import android.content.Context
import com.geekbrains.librariespopular.data.MockLoginApiImpl
import com.geekbrains.librariespopular.domain.LoginApi

class App : Application() {
    val loginApi: LoginApi by lazy { MockLoginApiImpl() }
}

val Context.app: App
    get() {
        return applicationContext as App
    }