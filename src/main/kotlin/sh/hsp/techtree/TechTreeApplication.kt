package sh.hsp.techtree

fun main(args: Array<String>) {
    createApplication(args.toList())
}

fun createApplication(args: List<String>) {
    TechTreeApplication(SimpleCommandLineParser()).run(args)
}