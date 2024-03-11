package sh.hsp.techtree
import java.io.IOException
import java.util.stream.Collectors

class TreeModelValidator {
    companion object {
        fun validate(treeModel: TreeModel) {
            val titleList = treeModel.nodes.map { it.title }
            val requiresList = treeModel.nodes.flatMap { it.requires.orEmpty() }

            if (!titleList.containsAll(requiresList)) {
                throw MissingRequiresNodeException("Validation failed because:\n" +
                        generateExceptionMessage(titleList, requiresList, treeModel)
                )
            }
        }
        private fun generateExceptionMessage(titleList: List<ID>, requiresList: List<ID>, treeModel: TreeModel): String {
            return requiresList.stream()
                .filter { it !in titleList }
                .map { "$it was not found and is required by ${findRequireParentTitle(treeModel, it)}" }
                .collect(Collectors.joining(",\n"))
        }
        private fun findRequireParentTitle(treeModel: TreeModel, require: ID): String {
            for (node in treeModel.nodes) {
                node.requires?.forEach { requiredId ->
                    if (requiredId == require) {
                        return node.title
                    }
                }
            }
            return ""
        }
    }
}
class MissingRequiresNodeException(message: String) : YamlReaderException(message)
open class YamlReaderException(message: String) : IOException(message)
