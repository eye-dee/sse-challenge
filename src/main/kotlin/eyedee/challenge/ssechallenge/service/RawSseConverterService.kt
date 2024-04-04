package eyedee.challenge.ssechallenge.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import eyedee.challenge.ssechallenge.model.Person
import eyedee.challenge.ssechallenge.model.RawSseResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.fold
import org.springframework.stereotype.Service

@Service
class RawSseConverterService(
    private val objectMapper: ObjectMapper,
) {
    suspend fun convertRawSse(rawSse: Flow<RawSseResponse>): Flow<Person> =
        rawSse
            .fold(StringBuilder()) { acc, element -> acc.append(element.data) }
            .let { objectMapper.readValue<List<Person>>(it.toString()) }
            .asFlow()
}
