package sh.hsp.techtree.graphviz

import sh.hsp.techtree.TreeModel

class GraphvizDSLConverter {
    fun convert(model: TreeModel):String {
        return "digraph { node "
    }
}