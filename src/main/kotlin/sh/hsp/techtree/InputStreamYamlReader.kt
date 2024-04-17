package sh.hsp.techtree

import com.fasterxml.jackson.databind.ObjectMapper
import sh.hsp.techtree.TreeModelValidator.Companion.validate
import java.io.InputStream

class InputStreamYamlReader(private val mapper: ObjectMapper): YamlReader {
    override fun readModel(input : InputStream) : TreeModel {
        val content = input.bufferedReader().use { it.readText() }
        val treeModel = mapper.readValue(content, TreeModel::class.java)
        validate(treeModel)
        return treeModel
    }
}