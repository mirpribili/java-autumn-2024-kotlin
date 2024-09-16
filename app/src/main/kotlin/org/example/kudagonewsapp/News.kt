package com.example.kudagonewsapp

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    val id: Int,
    val title: String,
    @Serializable(with = PlaceDeserializer::class)
    val place: String?,
    val description: String,
    @SerialName("site_url")
    val siteUrl: String = "",
    @SerialName("favorites_count")
    val favoritesCount: Int = 0,
    @SerialName("comments_count")
    val commentsCount: Int = 0,
    @SerialName("publication_date")
    val publicationDate: Long, // Изменено на Long
    //val rating: Double = 0.0
    // TODO rating place
) {
    val calculatedRating: Double by lazy {
        1 / (1 + Math.exp(-(favoritesCount.toDouble() / (commentsCount + 1))))
        //(1 + 1).toDouble()
    }

    // Метод для преобразования Int в LocalDate
    fun getPublicationDateAsLocalDate(): LocalDate {
        // Преобразование timestamp в дни и затем в Int
        return LocalDate.fromEpochDays((publicationDate / (24 * 60 * 60)).toInt())
    }

    // Переопределяем метод toString()
    override fun toString(): String {
        return "News(id=$id, title='$title', place=$place, description='$description', siteUrl='$siteUrl', " +
                "favoritesCount=$favoritesCount, commentsCount=$commentsCount, publicationDate=$publicationDate, " +
                "calculatedRating=$calculatedRating)"
    }
}