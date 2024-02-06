package sh.hsp.techtree.graphviz

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import sh.hsp.techtree.TreeModel
import sh.hsp.techtree.TreeNode

class DSLConverterTest {

    @Test
    fun givenTreeModelWithSingleNodeShouldRegisterSingleNodeInCorrectGrapvizDSL() {
        val model = TreeModel(listOf(TreeNode("node")))

        val converter = DSLConverter()

        val result = converter.convert(model)

        "digraph { node }".shouldBe(result)
    }

    @Test
    fun givenTreeModelWithMultipleSingularNodesShouldRegisterAllSingularNodesInCorrectGrapvizDSL() {
        val model = TreeModel(listOf(TreeNode("nodeA"), TreeNode("nodeB"), TreeNode("nodeC")))

        val converter = DSLConverter()

        val result = converter.convert(model)

        "digraph { nodeA nodeB nodeC }".shouldBe(result)
    }

    @Test
    fun givenTreeModelWithSingularNodeWithChildrenShouldRegisterNodesInCorrectGrapvizDSL() {
        val model = TreeModel(
            listOf(
                TreeNode("nodeA", listOf("nodeB", "nodeC")),
                TreeNode("nodeB"),
                TreeNode("nodeC")
            )
        )

        val converter = DSLConverter()

        val result = converter.convert(model)

        "digraph { nodeA nodeA -> nodeB nodeA -> nodeC nodeB nodeC }".shouldBe(result)
    }

}