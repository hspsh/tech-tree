package sh.hsp.techtree

class SimpleCommandLineParser : CommandLineParser {
    override fun run(args: List<String>, func: (inputFile: String) -> Unit) {
        func(args[0])
    }
}