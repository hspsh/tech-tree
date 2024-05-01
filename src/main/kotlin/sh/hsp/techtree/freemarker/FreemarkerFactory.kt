package sh.hsp.techtree.freemarker

import freemarker.template.Configuration
import sh.hsp.techtree.TreeNode
import java.io.File
import java.nio.file.Files
import kotlin.io.path.writer

class FreemarkerFactory(val configuration: Configuration = create()) {
    fun elementSvg(treeNode: TreeNode): File =
        configuration.getTemplate("element.svg").let { template ->
            Files.createTempFile("element", ".svg").also { tempFile ->
                template.process(treeNode, tempFile.writer())
            }.toFile()
        }

    companion object {
        fun create() = Configuration(Configuration.VERSION_2_3_32).apply {
            setClassForTemplateLoading(FreemarkerFactory::class.java, "/templates")
        }
    }
}