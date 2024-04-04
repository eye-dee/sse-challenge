package eyedee.challenge.ssechallenge.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import eyedee.challenge.ssechallenge.model.RawSseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader

@Component
class RawSseClient(
    private val rawSseApi: RawSseApi,
    private val objectMapper: ObjectMapper,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    suspend fun rawSse(): Flow<RawSseResponse> =
        rawSseApi.generateRawSse()
            .byteStream()
            .let { inputStream ->
                BufferedReader(inputStream.reader())
                    .lineSequence()
                    .map { it.removePrefix("data:") }
                    .filter { it.isNotBlank() }
                    .asFlow()
            }
            .map { objectMapper.readValue<RawSseResponse>(it) }
            .onEach {
                log.info("RawSseClient response received: ${it.data}")
            }
}
