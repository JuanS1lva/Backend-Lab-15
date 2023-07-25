import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.mockito.Mockito.*

class PatientControllerTest {
	@Mock
	private val patientService: PatientService? = null

	@InjectMocks
	private val patientController: PatientController? = null

	@Test
	fun testGetPatientById() {
		val patient = Patient(1L, "John", "Doe", "123456789", 30, "1234567890")

		`when`(patientService.getPatientById(1L)).thenReturn(patient)

		val response: ResponseEntity<Patient> = patientController.getPatientById(1L)

		assertEquals(HttpStatus.OK, response.getStatusCode())

		val retrievedPatient: Patient = response.getBody()
		assertNotNull(retrievedPatient)
		assertEquals(1L, retrievedPatient.getId())
		assertEquals("John", retrievedPatient.getNombre())
		assertEquals("Doe", retrievedPatient.getApellido())
		assertEquals("123456789", retrievedPatient.getNumeroCedula())
		assertEquals(30, retrievedPatient.getEdad())
		assertEquals("1234567890", retrievedPatient.getTelefono())

		verify(patientService, times(1)).getPatientById(1L)
	}

	@Test
	fun testGetAllPatients() {
		val patients: List<Patient> = ArrayList()
		patients.add(Patient(1L, "John", "Doe", "123456789", 30, "1234567890"))
		patients.add(Patient(2L, "Jane", "Smith", "987654321", 25, "9876543210"))

		`when`(patientService.getAllPatients()).thenReturn(patients)

		val response: ResponseEntity<List<Patient>> = patientController.getAllPatients()

		assertEquals(HttpStatus.OK, response.getStatusCode())

		val retrievedPatients: List<Patient> = response.getBody()
		assertNotNull(retrievedPatients)
		assertEquals(2, retrievedPatients.size())

		assertEquals(1L, retrievedPatients[0].getId())
		assertEquals("John", retrievedPatients[0].getNombre())
		assertEquals("Doe", retrievedPatients[0].getApellido())
		assertEquals("123456789", retrievedPatients[0].getNumeroCedula())
		assertEquals(30, retrievedPatients[0].getEdad())
		assertEquals("1234567890", retrievedPatients[0].getTelefono())
		assertEquals(2L, retrievedPatients[1].getId())
		assertEquals("Jane", retrievedPatients[1].getNombre())
		assertEquals("Smith", retrievedPatients[1].getApellido())
		assertEquals("987654321", retrievedPatients[1].getNumeroCedula())
		assertEquals(25, retrievedPatients[1].getEdad())
		assertEquals("9876543210", retrievedPatients[1].getTelefono())

		verify(patientService, times(1)).getAllPatients()
	}

	@Test
	fun testCreatePatient() {
		val newPatient = Patient("John", "Doe", "123456789", 30, "1234567890")

		`when`(patientService.createPatient(newPatient)).thenReturn(newPatient)

		val response: ResponseEntity<Patient> = patientController.createPatient(newPatient)

		assertEquals(HttpStatus.CREATED, response.getStatusCode())

		val createdPatient: Patient = response.getBody()
		assertNotNull(createdPatient)
		assertEquals("John", createdPatient.getNombre())
		assertEquals("Doe", createdPatient.getApellido())
		assertEquals("123456789", createdPatient.getNumeroCedula())
		assertEquals(30, createdPatient.getEdad())
		assertEquals("1234567890", createdPatient.getTelefono())

		verify(patientService, times(1)).createPatient(newPatient)
	}

	@Test
	fun testUpdatePatient() {
		val patientToUpdate = Patient(1L, "John", "Doe", "123456789", 30, "1234567890")

		`when`(patientService.updatePatient(patientToUpdate)).thenReturn(patientToUpdate)

		val response: ResponseEntity<Patient> = patientController.updatePatient(patientToUpdate)

		assertEquals(HttpStatus.OK, response.getStatusCode())

		val updatedPatient: Patient = response.getBody()
		assertNotNull(updatedPatient)
		assertEquals(1L, updatedPatient.getId())
		assertEquals("John", updatedPatient.getNombre())
		assertEquals("Doe", updatedPatient.getApellido())
		assertEquals("123456789", updatedPatient.getNumeroCedula())
		assertEquals(30, updatedPatient.getEdad())
		assertEquals("1234567890", updatedPatient.getTelefono())

		verify(patientService, times(1)).updatePatient(patientToUpdate)
	}
}
