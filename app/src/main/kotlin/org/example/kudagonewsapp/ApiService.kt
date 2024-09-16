package com.example.kudagonewsapp

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import kotlinx.serialization.decodeFromString

suspend fun getNews(count: Int = 100): List<News> {
    val client = HttpClient()
    val BASE_URL = "https://kudago.com/public-api/v1.4/news/"

    val response: HttpResponse = client.get(BASE_URL) {
        parameter("location", "spb")
        parameter("text_format", "text")
        parameter("expand", "place")
        parameter("fields", "id,title,place,description,site_url,favorites_count,comments_count,publication_date")
        parameter("page_size", count)
        parameter("order_by", "-publication_date")
    }

    val jsonString = response.bodyAsText()
    println(jsonString) // Выводим ответ для проверки

    val json = Json { ignoreUnknownKeys = true }

    // Десериализация JSON
    return json.decodeFromString<NewsResponse>(jsonString).results
}

// класс для обработки ответа API
@Serializable
data class NewsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<News>
)