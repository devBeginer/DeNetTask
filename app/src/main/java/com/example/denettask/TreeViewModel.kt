package com.example.denettask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TreeViewModel(private val repository: Repository) : ViewModel() {
    private var tree : Tree? = null

    private val loadChildren = MutableLiveData<List<TreeNode>>(emptyList())
    val children: LiveData<List<TreeNode>>
        get() = loadChildren

    private val loadCurrent = MutableLiveData<TreeNode>()
    val current: LiveData<TreeNode>
        get() = loadCurrent

    fun initRoot() {
        val node = tree?.root
        if (node != null){
            loadCurrent.postValue(node!!)
            loadChildren.postValue(node.children)
        }
    }


    fun select(node: TreeNode) {
        loadCurrent.postValue(node)
        loadChildren.postValue(node.children)
    }

    fun onBack() {
        val node = loadCurrent.value?.parent
        if (node != null){
            loadCurrent.postValue(node!!)
            loadChildren.postValue(node.children)
        }
    }

    fun delete(node: TreeNode) {
        tree?.let { it.deleteNode(node) }
        loadChildren.postValue(loadCurrent.value?.children)
    }

    fun add() {
        loadCurrent.value?.let { node-> tree?.let { tree -> tree.insertNode(node) } }
        loadChildren.postValue(loadCurrent.value?.children)
    }

    fun save() {
        viewModelScope.launch {
            tree?.let { repository.saveTree(it) }
        }
    }

    fun initTree() {
        viewModelScope.launch {
            tree = repository.buildTree(){
                tree = it
                val node = tree?.root
                if (node != null){
                    loadCurrent.postValue(node!!)
                    loadChildren.postValue(node.children)
                }
            }
        }
    }
}