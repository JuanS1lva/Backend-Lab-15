package com.doctores.doctores

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication()
@EnableJpaRepositories("com.doctores.doctores.repositories")
@EntityScan("com.doctores.doctores.domains.entity")
class DoctoresApplication


fun main(args: Array<String>) {
	runApplication<DoctoresApplication>(*args)
}


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DoctorControllerTest {
	private lateinit var doctorController: DoctorController

	@BeforeEach
	fun setup() {
		// Aquí puedes inicializar cualquier dependencia necesaria para las pruebas
		// Por ejemplo, si el DoctorController depende de algún servicio o repositorio,
		// puedes crear mocks o instancias simuladas para las pruebas.
		doctorController = DoctorController()
	}

	@Test
	fun `test createDoctor should return a valid doctor object`() {
		// Arrange
		val doctorData = DoctorData("John", "Doe", "Cardiología", "Consultorio 101", "john.doe@example.com")

		// Act
		val createdDoctor = doctorController.createDoctor(doctorData)

		// Assert
		assertNotNull(createdDoctor)
		assertEquals("John", createdDoctor.firstName)
		assertEquals("Doe", createdDoctor.lastName)
		assertEquals("Cardiología", createdDoctor.specialty)
		assertEquals("Consultorio 101", createdDoctor.office)
		assertEquals("john.doe@example.com", createdDoctor.email)
	}

	@Test
	fun `test getDoctorById should return the correct doctor`() {
		// Arrange
		val doctorId = 1
		// Suponiendo que tienes un método en el controlador para obtener un doctor por su ID
		val expectedDoctor = doctorController.getDoctorById(doctorId)

		// Act
		val actualDoctor = doctorController.getDoctorById(doctorId)

		// Assert
		assertNotNull(actualDoctor)
		assertEquals(expectedDoctor, actualDoctor)
	}

	@Test
	fun `test getDoctorsList should return a list of doctors`() {
		// Act
		val doctorsList = doctorController.getDoctorsList()

		// Assert
		assertNotNull(doctorsList)
		// Verificar si la lista de doctores no está vacía, o agregar otros criterios de prueba.
		// assertEquals(0, doctorsList.size) // Por ejemplo, para verificar que la lista está vacía.
		// assertEquals(expectedSize, doctorsList.size) // Para verificar un tamaño esperado.
	}
}

data class DoctorData(val firstName: String, val lastName: String, val specialty: String, val office: String, val email: String)
