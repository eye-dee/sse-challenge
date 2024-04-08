package eyedee.challenge.ssechallenge.endpoint

import eyedee.challenge.ssechallenge.client.RawSseClient
import eyedee.challenge.ssechallenge.model.Person
import eyedee.challenge.ssechallenge.service.RawSseConverterService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/persons")
class PersonEndpoint(
    private val rawSseClient: RawSseClient,
    private val rawSseConverterService: RawSseConverterService,
) {
    @PostMapping(
        path = ["all"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE],
    )
    suspend fun getAllPersons(): Flow<Person> =
        rawSseConverterService.convertRawSse(rawSseClient.rawSse())
}
