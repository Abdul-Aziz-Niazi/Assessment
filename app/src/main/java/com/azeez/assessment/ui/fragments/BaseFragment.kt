package com.azeez.assessment.ui.fragments

import androidx.fragment.app.Fragment
import com.azeez.assessment.ui.activities.MainActivity
import com.azeez.assessment.viewmodels.MainViewModel

open class BaseFragment : Fragment() {
    fun getMainViewModel(): MainViewModel {
        return (activity as MainActivity).viewModel
    }
}