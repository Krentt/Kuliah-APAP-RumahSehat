package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.JumlahModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ResepModelDTO {
    private Long id;
    private Boolean isDone =false;
    private LocalDateTime createdAt;
    private List<JumlahModel> listJumlahModel;
    private AppointmentModel appointment;
    private ApotekerModel apotekerModel;
}
