package com.example.denettask

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_child.*
import kotlinx.android.synthetic.main.fragment_root.*

class ChildFragment () : Fragment(R.layout.fragment_child) {
    private lateinit var recyclerViewAdapter: ItemAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        toolbar_child.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar_child.setNavigationOnClickListener() {
            findNavController().popBackStack()
            //onBackPressed() // возврат на предыдущий activity
        }
        recyclerViewAdapter = ItemAdapter( {
            findNavController().navigate(
                ChildFragmentDirections.actionChildFragmentToChildFragment(
                    it
                )
            )
        }, {
            //TODO onDelete
        })

        rv_children.adapter = recyclerViewAdapter
        rv_children.layoutManager = LinearLayoutManager(activity)

        fab_add.setOnClickListener {
            //TODO onCreate
        }
    }

}