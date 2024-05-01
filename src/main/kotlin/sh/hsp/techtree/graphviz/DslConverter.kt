package sh.hsp.techtree.graphviz

import sh.hsp.techtree.TreeModel

interface DslConverter {
    fun convert(model: TreeModel): String

    companion object {
        fun create(): DslConverter = DSLConverterImpl()
    }
}