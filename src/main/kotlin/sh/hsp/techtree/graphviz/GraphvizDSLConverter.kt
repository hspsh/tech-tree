package sh.hsp.techtree.graphviz

import sh.hsp.techtree.TreeModel
import sh.hsp.techtree.TreeNode
import java.util.stream.Collectors

class GraphvizDSLConverter {
    fun convert(model: TreeModel): String {
        return model.nodes.stream()
            .map { registerNode(it) }
            .map { "digraph { $it }" }
            .collect(Collectors.joining())
    }

    private fun registerNode(node: TreeNode): String {
        return node.title
    }
}