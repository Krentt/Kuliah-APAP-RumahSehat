package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.DokterModel;
import lombok.Data;

import java.time.YearMonth;
import java.util.List;

@Data
public class ChartDTO {
    private String method;
    private YearMonth yearMonth;
    private int year;
    private List<DokterModel> dokterModelList;
}
