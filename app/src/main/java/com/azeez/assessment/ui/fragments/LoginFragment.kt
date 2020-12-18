package com.azeez.assessment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.azeez.assessment.R
import com.azeez.assessment.extension.isValid
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {
    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginButton.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        if (isValid()) {
            getMainViewModel().login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            ).observe(viewLifecycleOwner, Observer {
                if (it.isSuccessful) {
                    movToNext()
                } else {
                    displayError(it.error)
                }
            })
        }
    }

    private fun displayError(error: String?) {
        Toast.makeText(requireActivity(), error ?: "Something went wrong", Toast.LENGTH_SHORT)
            .show()
    }

    private fun movToNext() {
        getMainViewModel().moveToViewDetails()
    }

    private fun isValid(): Boolean {
        return when {
            !usernameEditText.text.isValid() -> {
                displayError(getString(R.string.error_username_empty))
                false
            }
            !passwordEditText.text.isValid() -> {
                displayError(getString(R.string.error_password_empty))
                false
            }
            else -> true
        }
    }

    override fun onResume() {
        super.onResume()
        getMainViewModel().updateToolbar.postValue(LoginFragment::class.java.simpleName)
    }


}