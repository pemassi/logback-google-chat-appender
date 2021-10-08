package io.pemassi.logback.model

data class GoogleChatWebhookDto(
    val name: String? = null,
    val annotations: List<Any>? = null,
    val argumentText: String? = null,
    val cards: List<Card>? = null,
    val createTime: String? = null,
    val fallbackText: String? = null,
    val previewText: String? = null,
    val sender: Sender? = null,
    val space: Space? = null,
    val text: String? = null,
    val thread: Thread? = null,
)

data class Card(
    val cardActions: List<Any>? = null,
    val header: Header? = null,
    val name: String? = null,
    val sections: List<Any>? = null,
)

data class Header(
    val imageAltText: String? = null,
    val imageStyle: String? = null,
    val imageUrl: String? = null,
    val subtitle: String? = null,
    val title: String? = null,
)

data class Sender(
    val avatarUrl: String? = null,
    val displayName: String? = null,
    val email: String? = null,
    val name: String? = null,
    val type: String? = null,
)

data class Space(
    val displayName: String? = null,
    val name: String? = null,
    val type: String? = null,
)

data class Thread(
    val name: String? = null,
)