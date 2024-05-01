package sh.hsp.techtree.graphviz

import sh.hsp.techtree.TreeModel
import sh.hsp.techtree.TreeNode
import sh.hsp.techtree.freemarker.FreemarkerFactory
import java.util.stream.Collectors

class DSLConverter(val templating: FreemarkerFactory = FreemarkerFactory()) {
    fun convert(model: TreeModel): String {
        val nodesAsDSL = model.nodes.stream()
            .map { registerNode(it) }
            .collect(Collectors.joining(" "))

        return "digraph { graph [splines=\"ortho\"] node [shape=\"box\"] edge [dir=\"back\"] \n $nodesAsDSL }"
    }

    private fun registerNode(node: TreeNode): String {
        val pathsToChildren = node.requires?.let { require ->
            require.stream()
                .map { "${node.title.escaped()} -> ${it.escaped()}" }
                .collect(Collectors.joining(" "))
        } ?: ""
        return "${node.title.escaped()} ${node.link?.let { "[ image=${templating.elementSvg(node).toString().escaped()} href=${it.escaped()} fontcolor=blue ]" } ?: ""} $pathsToChildren \n"
    }

    private fun String.escaped(): String = "\"$this\""
}