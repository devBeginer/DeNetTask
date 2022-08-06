package com.example.denettask

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_child.*
import kotlinx.android.synthetic.main.fragment_root.*
import org.koin.android.ext.android.inject

class ChildFragment() : Fragment(R.layout.fragment_child) {
    private lateinit var recyclerViewAdapter: ItemAdapter
    private val viewModel: TreeViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        toolbar_child.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar_child.setNavigationOnClickListener() {
            viewModel.onBack()
            findNavController().popBackStack()
        }
        recyclerViewAdapter = ItemAdapter({
            viewModel.select(it)

            findNavController().navigate(
                ChildFragmentDirections.actionChildFragmentToChildFragment()
            )
        }, {
            viewModel.delete(it)
        })

        rv_children.adapter = recyclerViewAdapter
        rv_children.layoutManager = LinearLayoutManager(activity)

        fab_add.setOnClickListener {
            viewModel.add()

        }
        viewModel.current.observe(viewLifecycleOwner) {
            toolbar_child.title = it.name
        }
        viewModel.children.observe(viewLifecycleOwner) {
            recyclerViewAdapter.setItemList(it)
        }
    }

}