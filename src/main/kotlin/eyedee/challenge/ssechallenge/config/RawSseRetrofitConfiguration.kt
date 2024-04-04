package eyedee.challenge.ssechallenge.config

import eyedee.challenge.ssechallenge.client.RawSseApi
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.time.Duration

@Configuration
class RawSseRetrofitConfiguration(
    private val jacksonConverterFactory: JacksonConverterFactory,
    private val rawSseProviderProperties: RawSseProviderProperties,
) {
    @Bean
    fun rawSseRetrofit(): Retrofit =
        OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(10))
            .readTimeout(Duration.ofMinutes(50))
            .writeTimeout(Duration.ofMinutes(50))
            .let {
                Retrofit.Builder()
                    .baseUrl(rawSseProviderProperties.url)
                    .addConverterFactory(jacksonConverterFactory)
                    .client(it.build())
                    .build()
            }

    @Bean
    fun rawSseApi(rawSseRetrofit: Retrofit): RawSseApi = rawSseRetrofit.create(RawSseApi::class.java)
}
