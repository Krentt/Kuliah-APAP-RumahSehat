package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.JumlahModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResepModelDTO {
    private Long id;
    private Boolean isDone = false;
    private LocalDateTime createdAt;

    public ResepModelDTO(Long id, Boolean isDone, LocalDateTime createdAt, List<JumlahModel> listJumlahModel, AppointmentModel appointment, ApotekerModel apotekerModel) {
        this.id = id;
        this.isDone = isDone;
        this.createdAt = createdAt;
        this.listJumlahModel = listJumlahModel;
        this.appointment = appointment;
        this.apotekerModel = apotekerModel;
    }

    private List<JumlahModel> listJumlahModel;
    private AppointmentModel appointment;
    private ApotekerModel apotekerModel;

    public ResepModel toModel() {
        var resep = new ResepModel();
        resep.setId(this.id);
        resep.setIsDone(this.isDone);
        resep.setCreatedAt(this.createdAt);
        resep.setListJumlahModel(this.listJumlahModel);
        resep.setAppointment(this.appointment);
        resep.setApotekerModel(this.apotekerModel);
        return resep;
    }
}
