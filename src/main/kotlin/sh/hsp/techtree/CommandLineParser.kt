package sh.hsp.techtree

interface CommandLineParser {
    fun run(args: List<String>, func: (inputFile: String, outputFile: String) -> Unit)
}