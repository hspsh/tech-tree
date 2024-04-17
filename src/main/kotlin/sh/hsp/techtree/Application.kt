package sh.hsp.techtree

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import sh.hsp.techtree.graphviz.DSLConverter
import sh.hsp.techtree.graphviz.GraphvizCommandRunner
import sh.hsp.techtree.graphviz.SimpleGraphvizConverter
import java.io.FileInputStream
import java.nio.file.Paths

interface Application {
    fun run(args: List<String>)
}

class TechTreeApplication(private val commandLineParser: CommandLineParser) : Application {
    override fun run(args: List<String>) {
        commandLineParser.run(args) { parsedArgs ->
            TechTreeService(
                SimpleGraphvizConverter(DSLConverter(), GraphvizCommandRunner())
            ).execute(prepareYamlReader().readModel(if (parsedArgs.inputFile != null) FileInputStream(parsedArgs.inputFile) else System.`in`))
        }
    }

    private fun prepareYamlReader(): YamlReader {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

        return InputStreamYamlReader(mapper)
    }

}