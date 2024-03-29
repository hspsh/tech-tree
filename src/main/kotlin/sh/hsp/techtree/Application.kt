package sh.hsp.techtree

import sh.hsp.techtree.graphviz.DSLConverter
import sh.hsp.techtree.graphviz.GraphvizCommandRunner
import sh.hsp.techtree.graphviz.SimpleGraphvizConverter

interface Application {
    fun run(args: List<String>)
}

class TechTreeApplication(private val commandLineParser: CommandLineParser) : Application {
    override fun run(args: List<String>) {
        commandLineParser.run(args) { parsedArgs ->
            TechTreeService(
                FileSystemYamlReader(),
                SimpleGraphvizConverter(DSLConverter(), GraphvizCommandRunner())
            ).execute(parsedArgs)
        }
    }

}