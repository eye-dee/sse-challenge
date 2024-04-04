package eyedee.challenge.ssechallenge.client

import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.Streaming

interface RawSseApi {
    @POST("api/v1/sse/generate")
    @Streaming
    suspend fun generateRawSse(): ResponseBody
}
