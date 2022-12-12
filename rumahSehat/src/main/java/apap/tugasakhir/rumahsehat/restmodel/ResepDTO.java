package apap.tugasakhir.rumahsehat.restmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ResepDTO {
    private Long id;
    private boolean isDone;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdAt;
    private String namaApoteker;
    private List<Map<String, String>> listObat;
    private String namaDokter;
    private String namaPasien;

}
