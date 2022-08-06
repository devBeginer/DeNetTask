package com.example.denettask

class TreeNode(
    var parent: TreeNode?,
    val children: ArrayList<TreeNode> = arrayListOf()
) {
    var name: String = Utils.encrypt(this.toString()).drop(12)

    constructor(
        name: String,
        parent: TreeNode?,
        children: ArrayList<TreeNode> = arrayListOf()
    ): this(parent, children){
        this.name = name
    }

}