package sh.hsp.techtree

import java.nio.file.Paths

class TechTreeService(
    private val yamlReader: YamlReader,
    private val graphvizConverter: GraphvizConverter
) {

    fun execute(parsedArgs: CommandLineArguments) {
        val model = yamlReader.readToModel(Paths.get(parsedArgs.inputFile).toUri().toURL())
        graphvizConverter.convert(model, "can be null lol")
    }

}