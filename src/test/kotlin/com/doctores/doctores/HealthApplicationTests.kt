import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import org.junit.jupiter.api.Assertions.assertEquals

class HealthControllerTest {
	@InjectMocks
	private val healthController: HealthController? = null
	@Test
	fun testHealthCheck() {
		MockitoAnnotations.openMocks(this)

		val response: ResponseEntity<String> = healthController.healthCheck()

		assertEquals(HttpStatus.OK, response.getStatusCode())

		val responseBody: String = response.getBody()
		assertEquals("Servicio en buen estado", responseBody)
	}
}