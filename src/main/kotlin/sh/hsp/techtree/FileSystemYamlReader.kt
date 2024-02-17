package sh.hsp.techtree

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.io.IOException
import java.net.URL

class FileSystemYamlReader : YamlReader {
    override fun readToModel(resource: URL): TreeModel {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
        val file = File(resource.toURI())
        val yamlData = file.readText()
        val treeModel: TreeModel = mapper.readValue(yamlData)

        validateTreeModel(treeModel)

        return treeModel
    }

    private fun validateTreeModel(treeModel: TreeModel) {
        val titleList = treeModel.nodes.drop(1).map { it.title }
        val requiresList = treeModel.nodes.flatMap { it.requires.orEmpty() }

        if (titleList != requiresList) {
            throw YamlReaderException(generateExceptionMessage(titleList, requiresList, treeModel))
        }
    }

    private fun generateExceptionMessage(titleList: List<ID>, requiresList: List<ID>, treeModel: TreeModel): String {
        val mismatchDescriptions = mutableListOf<String>()
        mismatchDescriptions.add(findTitlesNotRequiredByAnyone(titleList, requiresList))
        mismatchDescriptions.add(findRequiredNodesNotFound(titleList, requiresList, treeModel))

        val nonEmptyDescriptions = mismatchDescriptions.filterNot { it.isEmpty() }

        return "Validation failed, because: \n${nonEmptyDescriptions.joinToString("\n")}"
    }

    private fun findTitlesNotRequiredByAnyone(titleList: List<ID>, requiresList: List<ID>): String {
        val titlesNotRequired = titleList.filterNot { it in requiresList }

        return titlesNotRequired.joinToString("\n") { "$it was found and is not required by any previous node" }
    }

    private fun findRequiredNodesNotFound(titleList: List<ID>, requiresList: List<ID>, treeModel: TreeModel): String {
        val errorMessage = StringBuilder()
        for (require in requiresList) {
            if (require !in titleList) {
                findRequireParentTitle(treeModel, errorMessage, require)
            }
        }
        return errorMessage.toString()
    }
}

private fun findRequireParentTitle(treeModel: TreeModel, errorMessage: StringBuilder, require: ID) {
    for (node in treeModel.nodes) {
        node.requires?.forEach { requiredId ->
            if (requiredId == require) {
                errorMessage.append("$require was not found and is required by ${node.title}\n")
            }
        }
    }
}

class YamlReaderException(message: String) : IOException(message)
