package com.geekbrains.librariespopular.login

class LoginContract {

    interface View {
        fun setSuccess()
        fun setError(error: String)
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onLogin(login: String, password: String)
        fun onCredentialsChanged()
    }
}