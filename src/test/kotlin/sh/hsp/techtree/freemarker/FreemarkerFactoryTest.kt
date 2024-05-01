package sh.hsp.techtree.freemarker

import org.junit.jupiter.api.Test
import sh.hsp.techtree.TreeNode


class FreemarkerFactoryTest {
    val treeNode = TreeNode(
        title = "Hello",
        requires = null,
        link = "https://hsp.sh/"
    )

    @Test
    fun readsTemplate() {
        FreemarkerFactory().elementSvg(treeNode)
    }
}