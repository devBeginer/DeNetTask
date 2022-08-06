package com.example.denettask

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_root.*
import org.koin.android.ext.android.inject

class RootFragment() : Fragment(R.layout.fragment_root) {
    private val viewModel:TreeViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        root_node_item.isClickable = true
        root_node_item.setOnClickListener { findNavController().navigate(
            RootFragmentDirections.actionRootFragmentToChildFragment()
        ) }
        viewModel.initRoot()

        viewModel.current.observe(viewLifecycleOwner) {
            tv_item_root_name.text = it.name
            tv_item_root_child_count.text = it.children.size.toString()
        }
    }
}