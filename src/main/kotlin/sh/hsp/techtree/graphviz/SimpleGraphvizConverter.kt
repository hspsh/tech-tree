package sh.hsp.techtree.graphviz

import sh.hsp.techtree.GraphvizConverter
import sh.hsp.techtree.TreeModel

class SimpleGraphvizConverter(
    private val dslConverter: DSLConverter,
    private val commandRunner: GraphvizCommandRunner
) : GraphvizConverter {
    override fun convert(model: TreeModel) {
        val dsl = dslConverter.convert(model)
        commandRunner.run(dsl)
    }
}