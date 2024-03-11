package sh.hsp.techtree

import sh.hsp.techtree.TreeModelValidator.Companion.validate
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.net.URL

class FileSystemYamlReader(private val mapper: ObjectMapper =
    ObjectMapper(YAMLFactory()).registerKotlinModule()) : YamlReader {
    override fun readToModel(resource: URL): TreeModel {
        return File(resource.toURI())
            .let { mapper.readValue(it, TreeModel::class.java) }
            .also { validate(it) }
    }
}
