package apap.tugasakhir.rumahsehat.restcontroller;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.restmodel.AppointmentDTO;
import apap.tugasakhir.rumahsehat.service.AppointmentService;
import apap.tugasakhir.rumahsehat.service.DokterService;
import apap.tugasakhir.rumahsehat.service.PasienService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/appointment")
public class AppointmentRESTController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    PasienService pasienService;
    @Autowired
    DokterService dokterService;

    String apiRespHeader = "header";

    @PostMapping("/add")
    public Map<String,Object> addAppointment(@Valid @RequestBody AppointmentDTO appt, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            log.info("Request body has invalid type or missing field");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            log.info("Access Add Appointment API");
            PasienModel pasien = pasienService.getPasienByUsername(appt.getPasienName());
            DokterModel dokter = dokterService.getDokterByUsername(appt.getDokterName());
            Map<String, Object> apiResp = new HashMap<>();

            if (appointmentService.checkJadwalPasien(appt.getWaktuAwal(), pasien) == null){

                if (appointmentService.checkJadwalDokter(appt.getWaktuAwal(), dokter) == null){
                    var appointment = new AppointmentModel();
                    appointment.setDone(false);
                    appointment.setPasienModel(pasienService.getPasienByUsername(appt.getPasienName()));
                    appointment.setDokterModel(dokterService.getDokterByUsername(appt.getDokterName()));
                    appointment.setWaktuAwal(appt.getWaktuAwal());
                    apiResp.put(apiRespHeader, "Success");
                    apiResp.put("body", appointmentService.createAppointment(appointment));
                } else {
                    log.info("[GAGAL ADD] Appointment Bentrok dengan jadwal Dokter!");
                    apiResp.put(apiRespHeader, "Error");
                    apiResp.put("userTrouble", "dokter");
                    apiResp.put("body", appointmentService.checkJadwalDokter(appt.getWaktuAwal(), dokter));
                }

            } else{
                log.info("[GAGAL ADD] Appointment Bentrok dengan jadwal Pasien!");
                apiResp.put(apiRespHeader, "Error");
                apiResp.put("userTrouble", "pasien");
                apiResp.put("body", appointmentService.checkJadwalPasien(appt.getWaktuAwal(), pasien));
            }
            return apiResp;

        }

    }

    @GetMapping("pasien-view-all")
    public List<AppointmentDTO> viewAllAppointmentPasien(@RequestHeader("Authorization") String token){
        log.info("Access View All Pasien API");
        Map<String, String> decodedToken = decode(token);
        PasienModel pasien = pasienService.getPasienByUsername(decodedToken.get("USERNAME"));
        List<AppointmentModel> listAppointmentPasien = pasien.getAppointmentPasien();
        List<AppointmentDTO> listAppointmentDTO = new ArrayList<>();
        for (AppointmentModel appt : listAppointmentPasien){
            var appointmentDTO = new AppointmentDTO();
            appointmentDTO.setKode(appt.getKode());
            appointmentDTO.setPasienName(pasien.getNama());
            appointmentDTO.setDokterName(appt.getDokterModel().getNama());
            appointmentDTO.setWaktuAwal(appt.getWaktuAwal());
            appointmentDTO.setDone(appt.isDone());
            if (appt.getResepModel() != null) {
                appointmentDTO.setKodeResep(appt.getResepModel().getId().toString());
            } else {
                appointmentDTO.setKodeResep(null);
            }

            listAppointmentDTO.add(appointmentDTO);
        }
        return listAppointmentDTO;
    }

    private Map<String, String> decode(String token) {
        String[] chunks = token.split("\\.");
        var decoder = Base64.getUrlDecoder();
        var payload = new String(decoder.decode(chunks[1]));
        var gson = new Gson();
        return gson.fromJson(payload, new TypeToken<Map<String, String>>() {}.getType());
    }

}
