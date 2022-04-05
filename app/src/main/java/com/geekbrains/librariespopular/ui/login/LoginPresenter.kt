package com.geekbrains.librariespopular.ui.login

import android.os.Handler
import android.os.Looper
import com.geekbrains.librariespopular.domain.LoginApi


class LoginPresenter(private val api: LoginApi) : LoginContract.Presenter {

    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var isSuccess: Boolean = false
    private var errorText: String = ""

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            val success = api.login(login, password)
            uiHandler.post {
                view?.hideProgress()
                if (success) {
                    view?.setSuccess()
                    isSuccess = true
                } else {
                    view?.setError("Неверный пароль")
                    isSuccess = false
                    errorText = "Неверный пароль"
                }
            }
        }.start()
    }

    override fun onCredentialsChanged() {
        //...
    }
}