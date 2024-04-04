package eyedee.challenge.ssechallenge.main

import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Lazy
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.time.Duration

@Lazy
@TestConfiguration
class SseApiConfig(
    private val jacksonConverterFactory: JacksonConverterFactory,
) {
    @Bean
    fun sseChallengeRetrofit(
        @Value("\${local.server.port}") serverPort: Int,
    ): Retrofit =
        OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(10))
            .readTimeout(Duration.ofMinutes(5))
            .writeTimeout(Duration.ofMinutes(5))
            .let {
                Retrofit.Builder()
                    .baseUrl("http://localhost:$serverPort")
                    .addConverterFactory(jacksonConverterFactory)
                    .client(it.build())
                    .build()
            }

    @Bean
    fun sseChallengeApi(sseChallengeRetrofit: Retrofit): SseChallengeApi = sseChallengeRetrofit.create(SseChallengeApi::class.java)
}
