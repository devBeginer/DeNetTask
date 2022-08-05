package com.example.denettask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

class ItemAdapter(val onClick: (name: String) -> Unit, val onDelete: (name: String) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var values: List<TreeNode> = ArrayList()

    fun setItemList(newList: List<TreeNode>) {
        notifyDataSetChanged()
        values = newList
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        holder.nameTextView?.text = values[position].name
        holder.childCountTextView?.text = values[position].children.size.toString()
        holder.deleteButton?.setOnClickListener {
            onDelete(values[position].name)
        }
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        var nameTextView: TextView? = null
        var childCountTextView: TextView? = null
        var deleteButton: Button? = null


        init {
            nameTextView = containerView.findViewById(R.id.tv_item_node_name)
            childCountTextView = containerView.findViewById(R.id.tv_item_child_count)
            deleteButton = containerView.findViewById(R.id.btn_item_delete_node)

            containerView.setOnClickListener {
                onClick(values[adapterPosition].name)
            }
        }
    }

}