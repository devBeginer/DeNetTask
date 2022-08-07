package com.example.denettask

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Repository(private val dao: Dao) {

    suspend fun buildTree(onResult: (tree: Tree) -> Unit): Tree {
        val nodes = dao.getRoot()
        val root: TreeNode
        if (nodes.isEmpty()) {
            root = TreeNode(null)
        } else {
            root = TreeNode(nodes.first().name, null)

            treeIterator(root, nodes.first())

        }
        val tree = Tree(root)

        println(tree)
        onResult(tree)

        return tree
    }

    suspend fun treeIterator(treeParent: TreeNode, dbParent: DBNode) {
        val dbChildren = dao.getNodesByParent(dbParent.id)
        val treeChildren = dbChildren.map { TreeNode(it.name, treeParent) }
        treeParent.children.addAll(treeChildren)

        for (children in dbChildren) treeIterator(
            treeChildren[dbChildren.indexOf(children)],
            children
        )
    }


    suspend fun saveTree(tree: Tree) {
        dao.dropTable()
        tree.iteration(tree.root) {
            runBlocking {
                launch {
                    val parent =
                        if (it.parent != null) dao.getNodeByName(it.parent!!.name)?.id else null
                    dao.insertNode(DBNode(name = it.name, parent = parent))
                }
            }
        }
    }
}