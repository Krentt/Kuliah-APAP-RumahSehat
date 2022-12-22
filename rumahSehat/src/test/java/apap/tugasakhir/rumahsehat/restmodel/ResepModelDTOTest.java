package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.JumlahModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class ResepModelDTOTest {

    @Test
    void constructor() {
        List<JumlahModel> jumlahModelList = new ArrayList<>();
        AppointmentModel appointmentModel = new AppointmentModel();
        ApotekerModel apotekerModel = new ApotekerModel();
        LocalDateTime time = LocalDateTime.now();

        ResepModelDTO resepModelDTO = new ResepModelDTO(
                1L,
                false,
                time,
                jumlahModelList,
                appointmentModel,
                apotekerModel
        );

        assertEquals(1L, resepModelDTO.getId());
        assertEquals(false, resepModelDTO.getIsDone());
        assertEquals(time, resepModelDTO.getCreatedAt());
        assertEquals(jumlahModelList, resepModelDTO.getListJumlahModel());
        assertEquals(appointmentModel, resepModelDTO.getAppointment());
        assertEquals(apotekerModel, resepModelDTO.getApotekerModel());
    }

    @Test
    void toModel() {
        List<JumlahModel> jumlahModelList = new ArrayList<>();
        AppointmentModel appointmentModel = new AppointmentModel();
        ApotekerModel apotekerModel = new ApotekerModel();

        ResepModelDTO resepModelDTO = new ResepModelDTO(
                1L,
                false,
                LocalDateTime.now(),
                jumlahModelList,
                appointmentModel,
                apotekerModel
        );

        assertEquals(ResepModel.class, resepModelDTO.toModel().getClass());
    }
}