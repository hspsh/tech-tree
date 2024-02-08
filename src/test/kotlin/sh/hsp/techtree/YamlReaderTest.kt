package sh.hsp.techtree

import io.kotest.matchers.equals.shouldBeEqual
import org.junit.jupiter.api.Test
import java.net.URL

class YamlReaderTest {
    private val yamlReader: YamlReader = FileSystemYamlReader()

    @Test
    fun givenTechTreeYamlWhenParsedThenModelIsCorrect() {
        val yamlUrl: URL? = YamlReader::class.java.getResource("/example.yaml")
        val model = yamlReader.readToModel(yamlUrl!!)
        model.shouldBeEqual(
            TreeModel(
                listOf(
                    TreeNode("miau", listOf("kot", "tygrys")),
                    TreeNode("kot", null),
                    TreeNode("tygrys", null)
                )
            )
        )
    }

}