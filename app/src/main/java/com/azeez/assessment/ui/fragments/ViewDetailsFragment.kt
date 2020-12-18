package com.azeez.assessment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.azeez.assessment.R
import kotlinx.android.synthetic.main.fragment_view_detail.*

class ViewDetailsFragment : BaseFragment() {
    companion object {
        fun newInstance() = ViewDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_detail, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDetailsButton.setOnClickListener {
            getMainViewModel().moveToListScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        getMainViewModel().updateToolbar.postValue(ViewDetailsFragment::class.java.simpleName)
    }
}