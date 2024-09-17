package com.example.kudagonewsapp

import java.io.File
import java.nio.file.Paths

fun saveNews(path: String, news: Collection<News>) {
    val file = Paths.get(path).toFile()

    if (file.exists()) {
        throw IllegalArgumentException("Файл уже существует по указанному пути")
    }

    file.bufferedWriter().use { writer ->
        writer.write("id,title,place,description,siteUrl,favoritesCount,commentsCount,publicationDate,rating\n")
        news.forEach { newsItem ->
            writer.write("${newsItem.id},\"${newsItem.title}\",\"${newsItem.place}\",\"${newsItem.description}\",${newsItem.siteUrl},${newsItem.favoritesCount},${newsItem.commentsCount},${newsItem.publicationDate},${newsItem.calculatedRating}\n")
        }
    }
}
fun deleteFileIfExists(filePath: String) {
    val file = File(filePath)
    if (file.exists()) {
        if (file.delete()) {
            println("Файл успешно удалён: $filePath")
        } else {
            println("Не удалось удалить файл: $filePath")
        }
    } else {
        println("Файл не существует: $filePath")
    }
}
