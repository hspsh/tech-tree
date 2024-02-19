package sh.hsp.techtree

class SimpleCommandLineParser : CommandLineParser {
    override fun run(args: List<String>, func: (parsedArgs: CommandLineArguments) -> Unit) {
        func(CommandLineArguments(args[0]))
    }
}