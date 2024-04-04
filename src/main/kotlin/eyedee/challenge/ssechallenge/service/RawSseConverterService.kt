package eyedee.challenge.ssechallenge.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import eyedee.challenge.ssechallenge.model.Person
import eyedee.challenge.ssechallenge.model.RawSseResponse
import org.springframework.stereotype.Service

@Service
class RawSseConverterService(
    private val objectMapper: ObjectMapper,
) {
    suspend fun convertRawSse(rawSse: List<RawSseResponse>): List<Person> =
        rawSse
            .joinToString(separator = "") { it.data }
            .let { objectMapper.readValue<List<Person>>(it) }
}
