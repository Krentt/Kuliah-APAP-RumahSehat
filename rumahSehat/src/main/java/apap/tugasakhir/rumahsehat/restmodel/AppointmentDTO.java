package apap.tugasakhir.rumahsehat.restmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO {
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime waktuAwal;
    private boolean isDone;
    private String pasienName;
    private String dokterName;
    private String kode;
    private String kodeResep;
}
