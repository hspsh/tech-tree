package sh.hsp.techtree
class TechTreeService(
    private val graphvizConverter: GraphvizConverter
) {
    fun execute(treeModel: TreeModel) {
        graphvizConverter.convert(treeModel)
    }

}