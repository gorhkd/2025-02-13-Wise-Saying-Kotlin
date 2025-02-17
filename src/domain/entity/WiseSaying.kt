package domain.entity

data class WiseSaying(val number: Int, val content: String, val author: String)

val quotes = mutableListOf<WiseSaying>()
