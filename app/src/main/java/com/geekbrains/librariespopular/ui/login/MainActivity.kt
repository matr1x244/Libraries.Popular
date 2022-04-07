package com.geekbrains.librariespopular.ui.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.geekbrains.librariespopular.R
import com.geekbrains.librariespopular.app
import com.geekbrains.librariespopular.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginContract.View {

    private var presenter: LoginContract.Presenter? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = restorePresenter()
        presenter?.onAttach(this)

        loginButton()
        backButton()
    }

    private fun restorePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    private fun loginButton() {
        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
        }
    }

    private fun backButton() {
        binding.backButton.setOnClickListener {
            binding.loginButton.isVisible = true
            binding.loginEditText.isVisible = true
            binding.passwordEditText.isVisible = true
            binding.successText.isVisible = false
            binding.backButton.isVisible = false
            binding.containerMainActivity.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    override fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.loginEditText.isVisible = false
        binding.passwordEditText.isVisible = false
        binding.successText.isVisible = true
        binding.backButton.isVisible = true
        binding.containerMainActivity.setBackgroundColor(Color.YELLOW)
        Toast.makeText(this, this.getString(R.string.success), Toast.LENGTH_SHORT).show()
    }

    override fun setError(error: String) {
        Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        binding.loginButton.isEnabled = false
    }

    override fun hideProgress() {
        binding.loginButton.isEnabled = true
        hideKeyboard(this)
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}