package com.example.denettask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TreeViewModel(private val repository: Repository) : ViewModel() {

    private val loadChildren = MutableLiveData<List<TreeNode>>(emptyList())
    val children: LiveData<List<TreeNode>>
        get() = loadChildren

    private val loadCurrent = MutableLiveData<TreeNode>()
    val current: LiveData<TreeNode>
        get() = loadCurrent

    fun initRoot() {
        viewModelScope.launch {
            val node = repository.getRoot()
            loadCurrent.postValue(node)
            loadChildren.postValue(repository.getChildren(node))
        }
    }


    fun select(name: String) {
        val node = current.value?.let { repository.getChild(it, name) }
        loadCurrent.postValue(node!!)
        loadChildren.postValue(repository.getChildren(node))
    }

    fun onBack() {
        val node = current.value?.let { repository.getParent(it) }
        if (node != null){
            loadCurrent.postValue(node!!)
            loadChildren.postValue(repository.getChildren(node))
        }
    }

    fun delete(name: String) {
        val node = current.value?.let { repository.getChild(it, name) }
        repository.deleteNode(node!!)
        loadChildren.postValue(current.value?.let { repository.getChildren(it) })
    }

    fun add() {
        current.value?.let { repository.insert(it) }
        loadChildren.postValue(current.value?.let { repository.getChildren(it) })
    }

    fun save() {
        viewModelScope.launch {
            repository.saveTree()
        }
    }

}