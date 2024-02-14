package sh.hsp.techtree

interface CommandLineParser {
    fun run(args: List<String>, func: (parsedArgs: CommandLineArguments) -> Unit)
}