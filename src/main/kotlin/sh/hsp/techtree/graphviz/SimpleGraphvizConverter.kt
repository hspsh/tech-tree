package sh.hsp.techtree.graphviz

import sh.hsp.techtree.GraphvizConverter
import sh.hsp.techtree.TreeModel

class SimpleGraphvizConverter(private val dslConverter: DSLConverter) : GraphvizConverter {
    override fun convert(model: TreeModel, outputPath: String) {
        val dsl = dslConverter.convert(model)
        // todo run dsl
        TODO("Not yet implemented")
    }
}