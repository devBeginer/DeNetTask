package com.example.denettask

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_root.*

class RootFragment() : Fragment(R.layout.fragment_root) {
    private lateinit var recyclerViewAdapter: ItemAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        root_node_item.isClickable = true
        root_node_item.setOnClickListener { findNavController().navigate(
            RootFragmentDirections.actionRootFragmentToChildFragment(
                it
            )
        ) }
    }
}