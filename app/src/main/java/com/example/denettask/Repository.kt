package com.example.denettask

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Repository(private val dao: Dao) {

    lateinit var tree: Tree

    init {
        runBlocking {
            launch {
                tree = buildTree()
            }
        }
    }

    fun getRoot(): TreeNode {
        return tree.root
    }

    suspend fun buildTree(): Tree {
        val nodes = dao.getRoot()
        val root: TreeNode
        if (nodes.isEmpty()) {
            root = TreeNode(null)
        } else {
            root = TreeNode(nodes.first().name, null)

            treeIterator(root, nodes.first())

        }


        return Tree(root)
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


    fun deleteNode(node: TreeNode) {
        tree.deleteNode(node)

    }

    fun getChild(current: TreeNode, name: String): TreeNode {
        val node = current.children.filter { it.name == name }
        return node.first()
    }

    fun getChildren(node: TreeNode): List<TreeNode> {
        return node.children
    }

    fun getParent(current: TreeNode): TreeNode? {
        return current.parent
    }

    fun insert(current: TreeNode) {
        tree.insertNode(current)
    }

    suspend fun saveTree() {
        dao.dropTable()
        tree.iteration(tree.root) {
            runBlocking {
                launch {
                    val parent =
                        if (it.parent != null) dao.getNodeByName(it.parent!!.name)?.id else null
                    val tmp = DBNode(name = it.name, parent = parent)
                    dao.insertNode(tmp)
                }
            }
        }
    }
}