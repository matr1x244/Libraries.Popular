package com.geekbrains.librariespopular.login

import android.os.Bundle
import android.view.View
import com.geekbrains.librariespopular.databinding.FragmentLoginBinding
import com.geekbrains.librariespopular.viewBindingFragment.ViewBindingFragment

class LoginFragment: ViewBindingFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    companion object{
        fun newInstance() = LoginFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}