package com.geekbrains.librariespopular.ui.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.geekbrains.librariespopular.app
import com.geekbrains.librariespopular.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var viewModel: LoginContract.ViewModel? = null
    private val handler: Handler by lazy { Handler(Looper.getMainLooper()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = restoreViewModel()

        loginButton()
        backButton()
        functionalOperation()
    }

    private fun functionalOperation() {
        viewModel?.shouldShowProgress?.subscribe(handler) { progress ->
            if (progress == true) {
                showProgress()
            } else {
                hideProgress()
            }
        }
        viewModel?.isSuccess?.subscribe(handler) { success ->
            if (success == true) {
                setSuccess()
                viewModel?.successText?.subscribe(handler) { successText ->
                    successText?.let {
                        val success = viewModel?.isSuccess?.value
                        if (success == true) {
                            setGood(successText)
                        }
                    }
                }
            }
        }
        viewModel?.errorText?.subscribe(handler) { errorText ->
            errorText?.let {
                val success = viewModel?.isSuccess?.value
                if (success == false) {
                    setError(errorText)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        functionalOperation().run {
            viewModel?.isSuccess?.unsubscribeAll()
            viewModel?.successText?.unsubscribeAll()
            viewModel?.errorText?.unsubscribeAll()
            viewModel?.shouldShowProgress?.unsubscribeAll()
        }
    }

    private fun restoreViewModel(): LoginViewModel {
        val viewModel = lastCustomNonConfigurationInstance as? LoginViewModel
        return viewModel ?: LoginViewModel(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return viewModel
    }

    private fun loginButton() {
        binding.loginButton.setOnClickListener {
            viewModel?.onLogin(
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

    private fun setSuccess() {
        binding.loginButton.isVisible = false
        binding.loginEditText.isVisible = false
        binding.passwordEditText.isVisible = false
        binding.successText.isVisible = true
        binding.backButton.isVisible = true
        binding.containerMainActivity.setBackgroundColor(Color.YELLOW)
    }

    private fun setGood(good: String) {
        Toast.makeText(this, "$good", Toast.LENGTH_SHORT).show()
    }

    private fun setError(error: String) {
        Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
    }

    private fun showProgress() {
        binding.loginButton.isEnabled = false
    }

    private fun hideProgress() {
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