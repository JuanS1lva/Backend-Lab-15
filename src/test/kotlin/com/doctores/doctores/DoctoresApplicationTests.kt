import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.ResponseEntity
import java.util.ArrayList
import java.util.List
import org.mockito.Mockito.*

class DoctorControllerTest {
	@Mock
	private val doctorService: DoctorService? = null

	@InjectMocks
	private val doctorController: DoctorController? = null
	@BeforeEach
	fun setUp() {
		MockitoAnnotations.openMocks(this)
	}

	@Test
	fun testGetAllDoctors() {
		val doctors: List<Doctor> = ArrayList()
		doctors.add(Doctor("John", "Doe", Especialidad.MEDICINA_GENERAL, "Consultorio A", "john.doe@example.com"))
		doctors.add(Doctor("Jane", "Smith", Especialidad.CARDIOLOGIA, "Consultorio B", "jane.smith@example.com"))
		`when`(doctorService.getAllDoctors()).thenReturn(doctors)
		val response: ResponseEntity<List<Doctor>> = doctorController.getAllDoctors()
		val resultDoctors: List<Doctor> = response.getBody()
		assertNotNull(resultDoctors)
		assertEquals(2, resultDoctors.size())
		assertEquals("John", resultDoctors[0].getNombre())
		assertEquals("Doe", resultDoctors[0].getApellido())
		assertEquals("Consultorio A", resultDoctors[0].getConsultorio())
		assertEquals("jane.smith@example.com", resultDoctors[1].getCorreoContacto())
	}

	@Test
	fun testGetDoctorById() {
		val doctor = Doctor("John", "Doe", Especialidad.MEDICINA_GENERAL, "Consultorio A", "john.doe@example.com")
		`when`(doctorService.getDoctorById(1L)).thenReturn(doctor)
		val response: ResponseEntity<Doctor> = doctorController.getDoctorById(1L)
		val resultDoctor: Doctor = response.getBody()
		assertNotNull(resultDoctor)
		assertEquals("John", resultDoctor.getNombre())
		assertEquals("Doe", resultDoctor.getApellido())
		assertEquals(Especialidad.MEDICINA_GENERAL, resultDoctor.getEspecialidad())
		assertEquals("john.doe@example.com", resultDoctor.getCorreoContacto())
	}

	@Test
	fun testCreateDoctor() {
		val newDoctor = Doctor("John", "Doe", Especialidad.MEDICINA_GENERAL, "Consultorio A", "john.doe@example.com")

		`when`(doctorService.createDoctor(newDoctor)).thenReturn(newDoctor)

		val response: ResponseEntity<Doctor> = doctorController.createDoctor(newDoctor)

		assertEquals(HttpStatus.CREATED, response.getStatusCode())

		val createdDoctor: Doctor = response.getBody()
		assertNotNull(createdDoctor)
		assertEquals("John", createdDoctor.getNombre())
		assertEquals("Doe", createdDoctor.getApellido())
		assertEquals(Especialidad.MEDICINA_GENERAL, createdDoctor.getEspecialidad())
		assertEquals("Consultorio A", createdDoctor.getConsultorio())
		assertEquals("john.doe@example.com", createdDoctor.getCorreoContacto())

		verify(doctorService, times(1)).createDoctor(newDoctor)
	}

	@Test
	fun testUpdateDoctor() {
		val doctorToUpdate = Doctor(1L, "John", "Doe", Especialidad.MEDICINA_GENERAL, "Consultorio A", "john.doe@example.com")

		`when`(doctorService.updateDoctor(doctorToUpdate)).thenReturn(doctorToUpdate)

		val response: ResponseEntity<Doctor> = doctorController.updateDoctor(doctorToUpdate)

		assertEquals(HttpStatus.OK, response.getStatusCode())

		val updatedDoctor: Doctor = response.getBody()
		assertNotNull(updatedDoctor)
		assertEquals(1L, updatedDoctor.getId())
		assertEquals("John", updatedDoctor.getNombre())
		assertEquals("Doe", updatedDoctor.getApellido())
		assertEquals(Especialidad.MEDICINA_GENERAL, updatedDoctor.getEspecialidad())
		assertEquals("Consultorio A", updatedDoctor.getConsultorio())
		assertEquals("john.doe@example.com", updatedDoctor.getCorreoContacto())

		verify(doctorService, times(1)).updateDoctor(doctorToUpdate)
	}
}
