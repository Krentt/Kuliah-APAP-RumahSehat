package apap.tugasAkhir.rumahSehat.service;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.DokterModel;

import java.util.List;

public interface ChartService {
    int[] getDataPendapatan(List<AppointmentModel> listAppointment);
    List<int[]> getDataLineTahun(int year, List<DokterModel> dokterModelList);

    List<int[]> getDataLineBulan(int year, int month, List<DokterModel> dokterModelList);
}
