package com.example.denettask

class Tree(val root: TreeNode = TreeNode(null)) {
    fun insertNode(parent: TreeNode): TreeNode{
        val newNode = TreeNode(parent)
        parent.children.add(newNode)
        return newNode
    }


    fun deleteNode(node: TreeNode){
        if (node != root){
            for (child in node.children) child.parent = node.parent
            node.parent!!.children.addAll(node.children)
            node.parent!!.children.remove(node)
        }
    }

    fun iteration(node: TreeNode, onIteration: (node: TreeNode) -> Unit)/*: ArrayList<TreeNode>*/{
        //val nodes = arrayListOf(node)
        onIteration(node)
        //for (child in node.children) nodes.addAll(iteration(child))
        for (child in node.children) iteration(child, onIteration)
        //return nodes
    }
}