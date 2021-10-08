package io.pemassi.logback

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.Layout
import ch.qos.logback.core.LayoutBase
import ch.qos.logback.core.UnsynchronizedAppenderBase
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.pemassi.logback.model.GoogleChatWebhookDto
import java.net.HttpURLConnection
import java.net.URL

class GoogleChatAppender : UnsynchronizedAppenderBase<ILoggingEvent>() {
    var webhookUri: String? = null
    var layout = defaultLayout
    var timeout = 30000

    override fun append(evt: ILoggingEvent) {
        try
        {
            if(!webhookUri.isNullOrBlank())
            {
                sendMessageWithWebhookUri(webhookUri!!, evt)
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
            addError("Error sending message to GoogleChat: $evt", e)
        }
    }

    private fun sendMessageWithWebhookUri(webhookUri: String, evt: ILoggingEvent) {
        val text = layout.doLayout(evt)

        val webhookDto = GoogleChatWebhookDto(
            text = text
        )

        val bytes = objectMapper.writeValueAsBytes(webhookDto)
        postMessage(webhookUri, "application/json", bytes)
    }

    private fun postMessage(uri: String, contentType: String, bytes: ByteArray) {
        val conn = URL(uri).openConnection() as HttpURLConnection
        conn.connectTimeout = timeout
        conn.readTimeout = timeout
        conn.doOutput = true
        conn.requestMethod = "POST"
        conn.setFixedLengthStreamingMode(bytes.size)
        conn.setRequestProperty("Content-Type", contentType)

        val os = conn.outputStream
        os.write(bytes)
        os.flush()
        os.close()
    }

    companion object {
        private val objectMapper = jacksonObjectMapper()

        private val defaultLayout: Layout<ILoggingEvent> = object : LayoutBase<ILoggingEvent>() {
            override fun doLayout(event: ILoggingEvent): String {
                return "-- [" + event.level + "]" +
                        event.loggerName + " - " +
                        event.formattedMessage.replace("\n".toRegex(), "\n\t")
            }
        }
    }
}