package sh.hsp.techtree

import java.net.URL

// Wiliniak
interface YamlReader {
    fun readToModel(resource: URL): TreeModel
}