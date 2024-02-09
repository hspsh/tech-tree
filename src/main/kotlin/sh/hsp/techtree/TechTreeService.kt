package sh.hsp.techtree

import java.nio.file.Paths

class TechTreeService(
    private val yamlReader: YamlReader,
    private val graphvizConverter: GraphvizConverter
) {

    fun execute(inputFile: String) {
        val model = yamlReader.readToModel(Paths.get(inputFile).toUri().toURL())
        graphvizConverter.convert(model, "can be null lol")
    }

}