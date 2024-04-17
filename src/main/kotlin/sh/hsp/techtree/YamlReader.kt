package sh.hsp.techtree

import java.io.InputStream

interface YamlReader {
    fun readModel(input : InputStream): TreeModel
}