package com.geekbrains.librariespopular.login

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import com.geekbrains.librariespopular.R
import com.geekbrains.librariespopular.databinding.FragmentLoginBinding
import com.geekbrains.librariespopular.viewBindingFragment.ViewBindingFragment

class LoginFragment: ViewBindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate),LoginContract.View {

    companion object{
        fun newInstance() = LoginFragment()
    }

    private var presenter: LoginContract.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRetainInstance(true) // сохраняем состоянии фрагмента
        presenter = LoginPresenter()
        presenter?.onAttach(this)

        loginButton()
    }

    private fun loginButton() {
        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    override fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.loginEditText.isVisible = false
        binding.passwordEditText.isVisible = false
        binding.fragmentContainerLogin.setBackgroundColor(resources.getColor(R.color.color_gray_transparent))
        Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()

    }

    override fun setError(error: String) {
        Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.loginButton.isEnabled = false
    }

    override fun hideProgress() {
        binding.loginButton.isEnabled = true
        hideKeyboard(requireActivity())
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}