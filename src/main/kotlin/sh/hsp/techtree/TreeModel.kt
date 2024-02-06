package sh.hsp.techtree

typealias ID = String
data class TreeModel(
    val nodes: List<TreeNode>
)

data class TreeNode(
    val title: ID,
    val requires: List<ID>?
)