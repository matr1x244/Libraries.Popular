package com.geekbrains.librariespopular.ui.login

import com.geekbrains.librariespopular.domain.LoginUsecase
import com.geekbrains.librariespopular.utils.Publisher


class LoginViewModel(
    private val loginUsecase: LoginUsecase
) : LoginContract.ViewModel {
    override val shouldShowProgress: Publisher<Boolean> = Publisher()
    override val isSuccess: Publisher<Boolean> = Publisher()
    override val errorText: Publisher<String?> = Publisher(true) //если одноразовое событие тогда true
    override val successText: Publisher<String?> = Publisher(true) //если одноразовое событие тогда true

    override fun onLogin(login: String, password: String) {
        shouldShowProgress.post(true)

        loginUsecase.login(login, password) { result ->
            shouldShowProgress.post(false)
            if (result) {
                isSuccess.post(true)
                successText.post("Успешно")
            } else {
                isSuccess.post(false)
                errorText.post("Неверный пароль!!!")
            }
        }
    }

    override fun onCredentialsChanged() {
        // todo
    }
}