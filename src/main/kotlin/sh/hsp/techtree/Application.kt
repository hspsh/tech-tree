package sh.hsp.techtree

interface Application {
    fun run(args: List<String>)
}

class TechTreeApplication : Application {
    override fun run(args: List<String>) {
        TODO("Not yet implemented")
    }

    fun create(args: List<String>) {
//        CommandParser().run(args, (inputFile, outputFile) -> {
//            TechTreeService(
//                YamlReader(),
//                GraphvizConverter()
//            ).execute(inputFile, outputFile)
//        })
    }
// java -jar tech-tree.jar inputfile.yaml outputFile.svg

}