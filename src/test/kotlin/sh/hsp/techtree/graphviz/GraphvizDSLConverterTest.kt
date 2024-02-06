package sh.hsp.techtree.graphviz

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import sh.hsp.techtree.TreeModel
import sh.hsp.techtree.TreeNode

class GraphvizDSLConverterTest {

    @Test
    fun givenTreeModelWithSingleNodeShouldRegisterSingleNodeInCorrectGrapvizDSL() {
        val model = TreeModel(listOf(TreeNode("node")))

        val converter = GraphvizDSLConverter()

        val result = converter.convert(model)

        "digraph { node }".shouldBe(result)
    }

}