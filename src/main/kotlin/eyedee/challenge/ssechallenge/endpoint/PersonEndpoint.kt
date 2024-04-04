package eyedee.challenge.ssechallenge.endpoint

import eyedee.challenge.ssechallenge.model.Person
import kotlinx.coroutines.flow.Flow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/persons")
class PersonEndpoint {

    @PostMapping(
        path = ["all"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE],
    )
    fun getAllPersons(): Flow<Person> = TODO()
}