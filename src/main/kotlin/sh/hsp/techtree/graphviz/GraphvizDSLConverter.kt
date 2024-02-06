package sh.hsp.techtree.graphviz

import sh.hsp.techtree.TreeModel
import sh.hsp.techtree.TreeNode
import java.util.stream.Collectors

class GraphvizDSLConverter {
    fun convert(model: TreeModel): String {
         val nodesAsDSL = model.nodes.stream()
            .map { registerNode(it) }
            .collect(Collectors.joining(" "))

        return "digraph { $nodesAsDSL }"
    }

    private fun registerNode(node: TreeNode): String {
        return node.title
    }
}