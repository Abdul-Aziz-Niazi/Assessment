package com.azeez.assessment.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azeez.assessment.R
import com.azeez.assessment.network.APISetup
import com.azeez.assessment.repository.BaseRepository
import com.azeez.assessment.ui.fragments.ListItemFragment
import com.azeez.assessment.ui.fragments.LoginFragment
import com.azeez.assessment.ui.fragments.ViewDetailsFragment
import com.azeez.assessment.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.toolbar_stub.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private lateinit var viewModelFactory: MainViewModel.MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelSetup()
        fragmentSetup()
        observerSetup()
    }

    private fun observerSetup() {
        viewModel.navigator.observe(this, Observer {
            when (it) {
                ViewDetailsFragment::class.java.simpleName -> goToViewDetails()
                ListItemFragment::class.java.simpleName -> goToList()
            }
        })

        viewModel.updateToolbar.observe(this, Observer {
            when (it) {
                LoginFragment::class.java.simpleName -> setupForLogin()
                ViewDetailsFragment::class.java.simpleName -> setupForViewDetails()
                ListItemFragment::class.java.simpleName -> setupForListItemScreen()
            }
        })
    }

    private fun goToViewDetails() {
        setupForViewDetails()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ViewDetailsFragment.newInstance())
            .addToBackStack(ViewDetailsFragment::class.java.simpleName)
            .commit()
    }

    private fun setupForViewDetails() {
        toolbarTitle.visibility = View.GONE
        subtitle.visibility = View.VISIBLE
        name.visibility = View.VISIBLE
        subtitle.text = getString(R.string.text_welcome)
        name.text = viewModel.username
    }

    private fun goToList() {
        setupForListItemScreen()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ListItemFragment.newInstance())
            .addToBackStack(ListItemFragment::class.java.simpleName)
            .commit()
    }

    private fun setupForListItemScreen() {
        toolbarTitle.visibility = View.GONE
        name.visibility = View.GONE
        subtitle.text = getString(R.string.text_details)
    }

    private fun fragmentSetup() {
        setupForLogin()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, LoginFragment.newInstance())
            .commit()
    }

    private fun setupForLogin() {
        subtitle.visibility = View.GONE
        name.visibility = View.GONE
        toolbarTitle.visibility = View.VISIBLE
    }

    private fun viewModelSetup() {
        viewModelFactory = MainViewModel.MainViewModelFactory(BaseRepository(APISetup.apiService))
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }
}