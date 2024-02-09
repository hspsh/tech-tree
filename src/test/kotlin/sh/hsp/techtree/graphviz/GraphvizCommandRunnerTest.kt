package sh.hsp.techtree.graphviz

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class GraphvizCommandRunnerTest {

    @Test
    fun whenValidDSLShouldRunCommandWithNoExceptions() {
        val commandRunner = GraphvizCommandRunner()
        assertDoesNotThrow { commandRunner.run("digraph { a -> b }") }
    }

    @Test
    fun whenInvalidDSLShouldThrowGraphvizCommandRunnerException() {
        val commandRunner = GraphvizCommandRunner()
        assertThrows<GraphvizCommandRunnerException> { commandRunner.run("XD") }
    }

}