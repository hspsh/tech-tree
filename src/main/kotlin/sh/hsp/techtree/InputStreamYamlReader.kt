package sh.hsp.techtree

import com.fasterxml.jackson.databind.ObjectMapper
import sh.hsp.techtree.TreeModelValidator.Companion.validate

class InputStreamYamlReader(private val mapper: ObjectMapper) : YamlReader {
    override fun readModel(): TreeModel {
        return System.`in`
            .bufferedReader()
            .use { it.readText() }
            .let { content -> mapper.readValue(content, TreeModel::class.java) }
            .also { validate(it) }
    }

}