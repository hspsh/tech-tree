package sh.hsp.techtree

// Ahiyos
interface GraphvizConverter {
    fun convert(model: TreeModel, outputPath: String)
}