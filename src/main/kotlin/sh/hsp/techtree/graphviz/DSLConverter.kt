package sh.hsp.techtree.graphviz

import sh.hsp.techtree.TreeModel
import sh.hsp.techtree.TreeNode
import java.util.stream.Collectors

class DSLConverter {
    fun convert(model: TreeModel): String {
         val nodesAsDSL = model.nodes.stream()
            .map { registerNode(it) }
            .collect(Collectors.joining(" "))

        return "digraph { edge [dir=\"back\"] $nodesAsDSL }"
    }

    private fun registerNode(node: TreeNode): String {
        if (node.requires != null) {
            val pathsToChildren = node.requires.stream()
                .map { "${node.title} -> $it" }
                .collect(Collectors.joining(" "))
            return "${node.title} $pathsToChildren"
        }

        return node.title
    }
}