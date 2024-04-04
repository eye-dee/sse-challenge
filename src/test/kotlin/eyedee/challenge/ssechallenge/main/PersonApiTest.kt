package eyedee.challenge.ssechallenge.main

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import eyedee.challenge.ssechallenge.model.Person
import eyedee.challenge.ssechallenge.service.RawSseGeneratorService.Companion.PERSON_LIST
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.io.BufferedReader
import kotlin.time.Duration.Companion.minutes

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
)
@Import(SseApiConfig::class)
@ActiveProfiles("test")
class PersonApiTest {
    @Autowired
    private lateinit var sseChallengeApi: SseChallengeApi

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `request person lists with sse`() =
        runTest(timeout = 10.minutes) {
            val res =
                sseChallengeApi.getPersons()
                    .byteStream()
                    .let { inputStream ->
                        BufferedReader(inputStream.reader())
                            .lineSequence()
                            .map { it.removePrefix("data:") }
                            .filter { it.isNotBlank() }
                    }
                    .map { objectMapper.readValue<Person>(it) }
                    .toList()

            assertThat(res)
                .containsExactlyInAnyOrderElementsOf(PERSON_LIST)
        }
}
