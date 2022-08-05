package com.example.denettask

class Tree(val root: TreeNode) {
    fun insertNode(parent: TreeNode): TreeNode{
        val newNode = TreeNode(parent)
        parent.children.add(newNode)
        return newNode
    }

    fun deleteNode(node: TreeNode){
        if (node != root){
            for (child in node.children) child.parent = node.parent
            node.parent!!.children.addAll(node.children)
        }
    }


    fun iteration(node: TreeNode){
        println(node.name)
        for (child in node.children) iteration(child)
    }
}