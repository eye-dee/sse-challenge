package eyedee.challenge.ssechallenge.endpoint

import eyedee.challenge.ssechallenge.model.RawSseResponse
import eyedee.challenge.ssechallenge.service.RawSseGeneratorService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/sse")
class RawSseEndpoint(
    private val rawSseGeneratorService: RawSseGeneratorService,
) {
    @PostMapping(
        path = ["generate"],
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE],
    )
    fun generateRawSseResponse(): Flow<RawSseResponse> = rawSseGeneratorService.generateRawSseResponse()
}
