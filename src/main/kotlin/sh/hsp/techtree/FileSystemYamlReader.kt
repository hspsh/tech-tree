package sh.hsp.techtree

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.net.URL

class FileSystemYamlReader : YamlReader {
    override fun readToModel(resource: URL): TreeModel {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
        val file = File(resource.toURI())
        val yamlData = file.readText()

        return mapper.readValue(yamlData)
    }

}