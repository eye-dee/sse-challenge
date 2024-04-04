package eyedee.challenge.ssechallenge.main

import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.Streaming

interface SseChallengeApi {
    @POST("api/v1/persons/all")
    @Streaming
    suspend fun betaStreamGenerateContent(): ResponseBody
}
