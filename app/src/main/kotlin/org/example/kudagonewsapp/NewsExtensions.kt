package com.example.kudagonewsapp

import kotlinx.datetime.LocalDate

fun List<News>.getMostRatedNewsUsingLoops(count: Int, period: ClosedRange<LocalDate>): List<News> {
    val filteredNews = mutableListOf<News>()

    for (news in this) {
        if (news.getPublicationDateAsLocalDate() in period) {
            filteredNews.add(news)
        }
    }

//    println("Рейтинги новостей перед сортировкой (Loops):")
//    filteredNews.forEach {
//        println("ID: ${it.id}, Title: ${it.title}, Rating: ${it.calculatedRating}")
//    }
    filteredNews.sortByDescending { it.calculatedRating }

    return filteredNews.take(count)
}

fun List<News>.getMostRatedNewsUsingSequences(count: Int, period: ClosedRange<LocalDate>): List<News> {
    return this.asSequence()
        .filter { it.getPublicationDateAsLocalDate() in period }
        .sortedByDescending { it.calculatedRating }
        .take(count)
        .toList()
}

