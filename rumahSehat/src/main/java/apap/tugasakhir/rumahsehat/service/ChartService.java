package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;

import java.util.List;

public interface ChartService {
    int[] getDataPendapatan(List<AppointmentModel> listAppointment);
    List<int[]> getDataLineTahun(int year, List<DokterModel> dokterModelList);

    List<int[]> getDataLineBulan(int year, int month, List<DokterModel> dokterModelList);
}
