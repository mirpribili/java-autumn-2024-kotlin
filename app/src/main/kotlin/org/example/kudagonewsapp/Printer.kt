package org.example.kudagonewsapp

import com.example.kudagonewsapp.News

class Printer {
    private val content = StringBuilder()

    fun header(text: String) {
        content.append("=== $text ===\n")
    }

    fun section(title: String, block: Printer.() -> Unit) {
        content.append("--- $title ---\n")
        val section = Printer()
        section.block()
        content.append(section.content.toString().prependIndent("  "))
        content.append("\n")
    }

    fun text(value: String) {
        content.append("$value\n")
    }

    fun bold(value: String): String = "**$value**"

    fun italic(value: String): String = "*$value*"

    fun link(url: String, text: String): String = "[$text]($url)"

    override fun toString(): String = content.toString()
}

// метолд для печати новостей
fun News.coolPrint(block: Printer.() -> Unit): String {
    val printer = Printer()
    printer.block()
    return printer.toString()
}