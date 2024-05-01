package sh.hsp.techtree

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import sh.hsp.techtree.graphviz.DslConverter
import sh.hsp.techtree.graphviz.GraphvizCommandRunner
import sh.hsp.techtree.graphviz.SimpleGraphvizConverter
import java.io.FileInputStream

interface Application {
    fun run(args: List<String>)
}

class TechTreeApplication(private val commandLineParser: CommandLineParser) : Application {
    override fun run(args: List<String>) {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
        commandLineParser.run(args) { parsedArgs ->
            TechTreeService(
                SimpleGraphvizConverter(DslConverter.create(), GraphvizCommandRunner())
            ).execute(readTreeModel(parsedArgs,mapper))
        }
    }

    private fun readTreeModel(parsedArgs: CommandLineArguments, mapper: ObjectMapper): TreeModel{
        val inputStream = if (parsedArgs.inputFile != null) FileInputStream(parsedArgs.inputFile) else System.`in`
        return InputStreamYamlReader(mapper).readModel(inputStream)
    }
}