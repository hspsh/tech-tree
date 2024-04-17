package sh.hsp.techtree

class SimpleCommandLineParser : CommandLineParser {
    override fun run(args: List<String>, func: (parsedArgs: CommandLineArguments) -> Unit) {
        val inputFile: String? = if (args.isNotEmpty()) args[0] else null
        func(CommandLineArguments(inputFile))
    }
}