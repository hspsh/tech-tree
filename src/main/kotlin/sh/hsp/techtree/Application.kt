package sh.hsp.techtree

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import sh.hsp.techtree.graphviz.DSLConverter
import sh.hsp.techtree.graphviz.GraphvizCommandRunner
import sh.hsp.techtree.graphviz.SimpleGraphvizConverter
import java.nio.file.Paths

interface Application {
    fun run(args: List<String>)
}

class TechTreeApplication(private val commandLineParser: CommandLineParser) : Application {
    override fun run(args: List<String>) {
        commandLineParser.run(args) { parsedArgs ->
            TechTreeService(
                SimpleGraphvizConverter(DSLConverter(), GraphvizCommandRunner())
            ).execute(prepareYamlReader(parsedArgs).readModel())
        }
    }

    private fun prepareYamlReader(parsedArgs: CommandLineArguments): YamlReader {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

        if (parsedArgs.inputFile != null) {
            return FileSystemYamlReader(mapper, Paths.get(parsedArgs.inputFile).toUri().toURL())
        }

        return InputStreamYamlReader(mapper)
    }

}