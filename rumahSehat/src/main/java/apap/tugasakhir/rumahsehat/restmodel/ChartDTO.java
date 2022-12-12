package apap.tugasakhir.rumahsehat.restmodel;

import apap.tugasakhir.rumahsehat.model.DokterModel;
import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;
import java.util.List;

@Getter
@Setter
public class ChartDTO {
    private String method;
    private YearMonth yearMonth;
    private int year;
    private List<DokterModel> dokterModelList;
}
