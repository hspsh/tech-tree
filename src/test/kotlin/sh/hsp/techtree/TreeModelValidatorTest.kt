package sh.hsp.techtree

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class TreeModelValidatorTest {
    @Test
    fun givenCorrectModelThenSucceeds() {
        val model = TreeModel(
            listOf
                (
                TreeNode("miau", listOf("kot", "tygrys")),
                TreeNode("kot"),
                TreeNode("tygrys"),
                TreeNode("pies")
            )
        )
        assertDoesNotThrow { TreeModelValidator.validate(model) }
    }

    @Test
    fun givenMissingRequiresNodeWhenValidatedThenThrowsYamlReaderException() {
        val model = TreeModel(
            listOf
                (
                TreeNode("miau", listOf("kot", "tygrys")),
                TreeNode("kot"),
                TreeNode("pies")
            )
        )
        assertThrows<MissingRequiresNodeException> { TreeModelValidator.validate(model) }
    }
}