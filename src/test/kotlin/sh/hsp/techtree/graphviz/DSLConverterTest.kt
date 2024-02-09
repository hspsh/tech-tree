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

        "digraph { graph [splines=\"ortho\"] node [shape=\"box\"] edge [dir=\"back\"] node }".shouldBe(result)
    }

    @Test
    fun givenTreeModelWithMultipleSingularNodesShouldRegisterAllSingularNodesInCorrectGrapvizDSL() {
        val model = TreeModel(listOf(TreeNode("nodeA"), TreeNode("nodeB"), TreeNode("nodeC")))

        val converter = DSLConverter()

        val result = converter.convert(model)

        "digraph { graph [splines=\"ortho\"] node [shape=\"box\"] edge [dir=\"back\"] nodeA nodeB nodeC }".shouldBe(result)
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

        "digraph { graph [splines=\"ortho\"] node [shape=\"box\"] edge [dir=\"back\"] nodeA nodeA -> nodeB nodeA -> nodeC nodeB nodeC }".shouldBe(result)
    }

}