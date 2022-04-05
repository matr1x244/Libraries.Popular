package com.geekbrains.librariespopular.ui.login

import android.os.Handler
import android.os.Looper
import java.lang.Thread.sleep


class LoginPresenter : LoginContract.Presenter {

    private var view: LoginContract.View? = null
    private val uiHandler = Handler(Looper.getMainLooper())
    private var isSuccess: Boolean = false
    private var errorText: String = ""

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        } else {
            view.setError(errorText)
        }
    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        Thread {
            sleep(3000)
            uiHandler.post {
                view?.hideProgress()
                if (checkCredentials(login, password)) {
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

    /*сравниваем логин и пароль*/
    private fun checkCredentials(login: String, password: String): Boolean {
        return login == password
    }

    override fun onCredentialsChanged() {
        //...
    }
}