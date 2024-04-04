package eyedee.challenge.ssechallenge.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.converter.jackson.JacksonConverterFactory

@Configuration
class RetrofitConfiguration(
    private val objectMapper: ObjectMapper,
) {
    @Bean
    fun jacksonConverterFactory(): JacksonConverterFactory =
        JacksonConverterFactory.create(objectMapper)
}
