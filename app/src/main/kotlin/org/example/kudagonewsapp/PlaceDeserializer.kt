package com.example.kudagonewsapp

import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.*

object PlaceDeserializer : JsonTransformingSerializer<String>(String.serializer()) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        return when (element) {
            is JsonObject -> JsonPrimitive(element["title"]?.jsonPrimitive?.content ?: "Неизвестно")
            is JsonNull -> JsonPrimitive("Неизвестно")
            else -> element
        }
    }
}