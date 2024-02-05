package sh.hsp.techtree

// Ahiyos - changed
interface GraphvizConverter {
    fun convert(model: TreeModel, outputPath: String)
}