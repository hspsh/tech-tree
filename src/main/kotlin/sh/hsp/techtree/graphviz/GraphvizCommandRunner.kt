package sh.hsp.techtree.graphviz

import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

class GraphvizCommandRunnerException(message: String) : RuntimeException(message)

class GraphvizCommandRunner {
    fun run(dsl: String) {
        ProcessBuilder()
            .redirectInput(ProcessBuilder.Redirect.PIPE)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.INHERIT)
            .command("dot", "-Tsvg").start()
            .apply {
                outputStream.writer()
                    .apply { write(dsl) }
                    .close()

                waitFor()

                inputStream.reader().readText().let { transform(it) }
            }
    }


    fun transform(xml: String) {
        val builder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder()
        val document = builder.parse(xml.byteInputStream())
        val xpath = XPathFactory.newDefaultInstance().newXPath()



        (xpath.evaluate("//image", document, XPathConstants.NODESET) as NodeList).let { nodes ->
            (0..<nodes.length).map {
                nodes.item(it).also { node ->
                    System.err.println(node?.parentNode)

                    val externalSvg = builder.parse(File(node.attributes.getNamedItem("xlink:href").nodeValue))

                    val externalImage: Node = document.importNode(
                        (externalSvg as Document).documentElement,
                        true
                    )

                    externalImage.attributes.apply {
                        setNamedItem(
                            document.createAttribute("x")
                                .apply { textContent = node.attributes.getNamedItem("x").textContent }
                        )
                        setNamedItem(
                            document.createAttribute("y")
                                .apply { textContent = node.attributes.getNamedItem("y").textContent }
                        )
                        setNamedItem(
                            document.createAttribute("width")
                                .apply { textContent = node.attributes.getNamedItem("width").textContent }
                        )
                        setNamedItem(
                            document.createAttribute("height")
                                .apply { textContent = node.attributes.getNamedItem("height").textContent }
                        )
                    }


                    node?.parentNode?.replaceChild(
                        externalImage,
                        node
                    )
                }
            }
        }

        DOMSource(document).let {
            val transformer = TransformerFactory.newDefaultInstance().newTransformer()
            transformer.transform(it, StreamResult(System.out))
        }
    }
}