package sh.hsp.techtree.graphviz

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import sh.hsp.techtree.TreeModel
import sh.hsp.techtree.TreeNode

class DSLConverterTest {

    private val sut = DSLConverter()
    @Test
    fun givenTreeModelWithSingleNodeShouldRegisterSingleNodeInCorrectGrapvizDSL() {
        val model = TreeModel(listOf(TreeNode("node")))

        val result = sut.convert(model)

        "digraph { graph [splines=\"ortho\"] node [shape=\"box\"] edge [dir=\"back\"] node }".shouldBe(result)
    }

    @Test
    fun givenTreeModelWithMultipleSingularNodesShouldRegisterAllSingularNodesInCorrectGrapvizDSL() {
        val model = TreeModel(listOf(TreeNode("nodeA"), TreeNode("nodeB"), TreeNode("nodeC")))

        val result = sut.convert(model)

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

        val result = sut.convert(model)

        "digraph { graph [splines=\"ortho\"] node [shape=\"box\"] edge [dir=\"back\"] nodeA nodeA -> nodeB nodeA -> nodeC nodeB nodeC }".shouldBe(result)
    }

    @Test
    fun givenSpacesInTitleShouldRegisterNodesInCorrectGraphvizDSL() {
        val model = TreeModel(
            listOf(
                TreeNode("Big dog", listOf("Small dog")),
                TreeNode("Small dog"),
                TreeNode("Big cat")
            )
        )

        val result = sut.convert(model)

        "digraph { graph [splines=\"ortho\"] node [shape=\"box\"] edge [dir=\"back\"] \"Big dog\" \"Big dog\" -> \"Small dog\" \"Small dog\" \"Big cat\" }".shouldBe(result)
    }

}