package apap.tugasakhir.rumahsehat;

import apap.tugasakhir.rumahsehat.controller.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class RumahSehatApplicationTests {

	@Autowired
	AppointmentController appointmentController;
	ChartController chartController;
	HomeController homeController;
	ObatController obatController;
	ResepController resepController;
	UserController userController;

	@Test
	void contextLoads() {
		assertThat(appointmentController).isNotNull();
		assertThat(chartController).isNotNull();
		assertThat(homeController).isNotNull();
		assertThat(obatController).isNotNull();
		assertThat(resepController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
