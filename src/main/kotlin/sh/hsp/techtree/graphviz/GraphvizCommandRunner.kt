package sh.hsp.techtree.graphviz

import runCommand

class GraphvizCommandRunnerException(message: String): RuntimeException(message)

class GraphvizCommandRunner {
    fun run(dsl: String) {
        val command = "echo \'$dsl\' | dot -Tsvg"

        val result = command.runCommand {  }

        if (result.exitValue() != 0) {
            throw GraphvizCommandRunnerException("Error running: $command")
        }

    }

}