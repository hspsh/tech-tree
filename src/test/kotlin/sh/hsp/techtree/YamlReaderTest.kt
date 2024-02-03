package sh.hsp.techtree

import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test

class YamlReaderTest {
    val yamlReader: YamlReader? = null;


    @Test
    fun givenTechTreeYamlWhenParsedThenModelIsCorrect() {
        val yamlUrl = YamlReader::class.java.getResource("/example.yaml")

        // !! .?
        val model = yamlReader!!.readToModel(yamlUrl!!)

        // https://kotlinlang.org/docs/extensions.html
        model.shouldBeEqual(
            TreeModel(
                listOf(
                    TreeNode("miau", listOf("tygrys", "kot")),
                    TreeNode("tygrys", listOf()),
                    TreeNode("kot", listOf())
                )
            )
        )
    }

}
