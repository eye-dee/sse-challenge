package eyedee.challenge.ssechallenge.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "rawsse")
class RawSseProviderProperties {
    lateinit var url: String
}
