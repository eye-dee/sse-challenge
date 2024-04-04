package eyedee.challenge.ssechallenge.service

import com.fasterxml.jackson.databind.ObjectMapper
import eyedee.challenge.ssechallenge.model.Person
import eyedee.challenge.ssechallenge.model.RawSseResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class RawSseGeneratorService(
    private val objectMapper: ObjectMapper,
) {
    fun generateRawSseResponse(): Flow<RawSseResponse> =
        flow {
            objectMapper.writeValueAsString(PERSON_LIST)
                .chunked(10)
                .map {
                    RawSseResponse(
                        data = it,
                        statusCode = 10,
                        headers =
                            mapOf(
                                "barca" to "elon",
                            ),
                        isSuccess = true,
                        timestamp = Instant.now().toEpochMilli(),
                    )
                }
                .forEach {
                    emit(it)
                    delay(100)
                }
        }

    companion object {
        val PERSON_LIST: List<Person> =
            listOf(
                Person("Scooter McGee", 42, "Professional Juggler", "I can juggle anything except my responsibilities!"),
                Person("Betty Bananas", 29, "Banana Farmer", "I'm bananas for bananas!"),
                Person("Rocky Road", 35, "Ice Cream Taster", "Life is like ice cream, enjoy it before it melts!"),
                Person("Candy Cane", 25, "Candy Maker", "I make candy disappear, what's your superpower?"),
                Person("Morty McFly", 18, "Time Travel Enthusiast", "1.21 gigawatts? I thought you said 1.21 jiggawatts!"),
                Person(
                    "Penny Poppins",
                    39,
                    "Umbrella Repair Specialist",
                    "A spoonful of sugar helps the umbrella stay closed!",
                ),
                Person("Barry Bumblebee", 31, "Flower Pollinator", "Buzz buzz, I'm on a mission from the queen!"),
                Person("Alice in Wanderland", 27, "Professional Daydreamer", "Why walk when you can wander?"),
                Person("Sammy Sock", 33, "Sock Puppeteer", "I'm a sock, but I've got a lot of sole!"),
                Person("Polly Parrot", 36, "Pirate Parrot", "Ahoy matey! Shiver me timbers and pass the crackers!"),
            )
    }
}
