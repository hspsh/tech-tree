package sh.hsp.techtree.graphviz

import runCommand

class GraphvizCommandRunnerException(message: String) : RuntimeException(message)

class GraphvizCommandRunner {
    fun run(dsl: String) {
        ProcessBuilder()
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .command("dot", "-Tsvg").start()
            .apply {
                outputStream.writer()
                    .apply { write(dsl) }
                    .close()
            }
            .apply {
                waitFor().let {
                    if (it != 0) throw GraphvizCommandRunnerException("Error Running Graphviz - return code $it")
                }
            }
    }
}