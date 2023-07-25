import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.ArrayList
import java.util.List
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.mockito.Mockito.*

class AppointmentControllerTest {
	@Mock
	private val appointmentService: AppointmentService? = null

	@InjectMocks
	private val appointmentController: AppointmentController? = null
	@BeforeEach
	fun setUp() {
		MockitoAnnotations.openMocks(this)
	}

	@Test
	fun testGetAllAppointments() {

		val appointments: List<Appointment> = ArrayList()
		appointments.add(Appointment(1L, "John", Especialidad.MEDICINA_GENERAL))
		appointments.add(Appointment(2L, "Jane", Especialidad.CARDIOLOGIA))

		`when`(appointmentService.getAllAppointments()).thenReturn(appointments)

		val response: ResponseEntity<List<Appointment>> = appointmentController.getAllAppointments()

		assertEquals(HttpStatus.OK, response.getStatusCode())

		val retrievedAppointments: List<Appointment> = response.getBody()
		assertNotNull(retrievedAppointments)
		assertEquals(2, retrievedAppointments.size())

		assertEquals(1L, retrievedAppointments[0].getId())
		assertEquals("John", retrievedAppointments[0].getNombre())
		assertEquals(Especialidad.MEDICINA_GENERAL, retrievedAppointments[0].getEspecialidad())
		assertEquals(2L, retrievedAppointments[1].getId())
		assertEquals("Jane", retrievedAppointments[1].getNombre())
		assertEquals(Especialidad.CARDIOLOGIA, retrievedAppointments[1].getEspecialidad())

		verify(appointmentService, times(1)).getAllAppointments()
	}

	@Test
	fun testCreateAppointment() {
		val newAppointment = Appointment("John", Especialidad.MEDICINA_GENERAL)

		`when`(appointmentService.createAppointment(newAppointment)).thenReturn(newAppointment)

		val response: ResponseEntity<Appointment> = appointmentController.createAppointment(newAppointment)

		assertEquals(HttpStatus.CREATED, response.getStatusCode())

		val createdAppointment: Appointment = response.getBody()
		assertNotNull(createdAppointment)
		assertEquals("John", createdAppointment.getNombre())
		assertEquals(Especialidad.MEDICINA_GENERAL, createdAppointment.getEspecialidad())

		verify(appointmentService, times(1)).createAppointment(newAppointment)
	}

	@Test
	fun testGetAppointmentById() {
		val appointment = Appointment(1L, "John", Especialidad.MEDICINA_GENERAL)

		`when`(appointmentService.getAppointmentById(1L)).thenReturn(appointment)

		val response: ResponseEntity<Appointment> = appointmentController.getAppointmentById(1L)

		assertEquals(HttpStatus.OK, response.getStatusCode())

		val retrievedAppointment: Appointment = response.getBody()
		assertNotNull(retrievedAppointment)
		assertEquals(1L, retrievedAppointment.getId())
		assertEquals("John", retrievedAppointment.getNombre())
		assertEquals(Especialidad.MEDICINA_GENERAL, retrievedAppointment.getEspecialidad())

		verify(appointmentService, times(1)).getAppointmentById(1L)
	}

	@Test
	fun testUpdateAppointment() {
		val appointmentToUpdate = Appointment(1L, "John", Especialidad.MEDICINA_GENERAL)

		`when`(appointmentService.updateAppointment(appointmentToUpdate)).thenReturn(appointmentToUpdate)

		val response: ResponseEntity<Appointment> = appointmentController.updateAppointment(appointmentToUpdate)

		assertEquals(HttpStatus.OK, response.getStatusCode())

		val updatedAppointment: Appointment = response.getBody()
		assertNotNull(updatedAppointment)
		assertEquals(1L, updatedAppointment.getId())
		assertEquals("John", updatedAppointment.getNombre())
		assertEquals(Especialidad.MEDICINA_GENERAL, updatedAppointment.getEspecialidad())

		verify(appointmentService, times(1)).updateAppointment(appointmentToUpdate)
	}
}