package sh.hsp.techtree.graphviz

import sh.hsp.techtree.GraphvizConverter
import sh.hsp.techtree.TreeModel

class SimpleGraphvizConverter(
    private val dslConverter: DslConverter,
    private val commandRunner: GraphvizCommandRunner
) : GraphvizConverter {
    override fun convert(model: TreeModel) {
        val dsl = dslConverter.convert(model)
        System.err.println(dsl)
        commandRunner.run(dsl)
    }
}