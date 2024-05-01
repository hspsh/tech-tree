package sh.hsp.techtree

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

typealias ID = String

@JsonIgnoreProperties(ignoreUnknown = true)
data class TreeModel(
    val nodes: List<TreeNode>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class TreeNode(
    val title: ID,
    val requires: List<ID>? = null,
    val link: String? = null,
    val docs: String? = null,
    val repository: String? = null,
    val image: String? = null
)