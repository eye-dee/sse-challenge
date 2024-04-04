package eyedee.challenge.ssechallenge.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import eyedee.challenge.ssechallenge.model.RawSseResponse
import org.springframework.stereotype.Component
import java.io.BufferedReader

@Component
class RawSseClient(
    private val rawSseApi: RawSseApi,
    private val objectMapper: ObjectMapper,
) {
    suspend fun rawSse(): List<RawSseResponse> =
        rawSseApi.generateRawSse()
            .byteStream()
            .let { inputStream ->
                BufferedReader(inputStream.reader())
                    .lineSequence()
                    .map { it.removePrefix("data:") }
                    .filter { it.isNotBlank() }
            }
            .map { objectMapper.readValue<RawSseResponse>(it) }
            .toList()
}
