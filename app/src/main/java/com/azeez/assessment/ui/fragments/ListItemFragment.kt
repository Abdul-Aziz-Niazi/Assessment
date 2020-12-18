package com.azeez.assessment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.azeez.assessment.R
import com.azeez.assessment.adapters.ProductItemAdapter
import kotlinx.android.synthetic.main.fragment_list.*

class ListItemFragment : BaseFragment() {
    private val adapter = ProductItemAdapter()

    companion object {
        fun newInstance() = ListItemFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMainViewModel().getItems().observe(viewLifecycleOwner, {
            if (!(it.isSuccessful && it.data?.itemList?.isNullOrEmpty() == true)) {
                productRV.layoutManager = LinearLayoutManager(requireContext())
                adapter.items = it.data?.itemList ?: arrayListOf()
                productRV.adapter = adapter
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getMainViewModel().updateToolbar.postValue(ListItemFragment::class.java.simpleName)
    }
}