package eyedee.challenge.ssechallenge.model

data class RawSseResponse(
    val data: String,
    val statusCode: Int,
    val headers: Map<String, String>,
    val isSuccess: Boolean,
    val timestamp: Long,
)
