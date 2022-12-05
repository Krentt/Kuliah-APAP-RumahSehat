package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.restModel.AppointmentDTO;
import apap.tugasAkhir.rumahSehat.service.AppointmentService;
import apap.tugasAkhir.rumahSehat.service.DokterService;
import apap.tugasAkhir.rumahSehat.service.PasienService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentRESTController {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    PasienService pasienService;
    @Autowired
    DokterService dokterService;

    @PostMapping("/add")
    private Map<String,Object> addAppointment(@Valid @RequestBody AppointmentDTO appt, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            PasienModel pasien = pasienService.getPasienByUsername(appt.getPasienName());
            DokterModel dokter = dokterService.getDokterByUsername(appt.getDokterName());
            Map<String, Object> apiResp = new HashMap<>();

            if (appointmentService.checkJadwalPasien(appt.getWaktuAwal(), pasien) == null){

                if (appointmentService.checkJadwalDokter(appt.getWaktuAwal(), dokter) == null){
                    AppointmentModel appointment = new AppointmentModel();
                    appointment.setDone(false);
                    appointment.setPasienModel(pasienService.getPasienByUsername(appt.getPasienName()));
                    appointment.setDokterModel(dokterService.getDokterByUsername(appt.getDokterName()));
                    appointment.setWaktuAwal(appt.getWaktuAwal());
                    apiResp.put("header", "Success");
                    apiResp.put("body", appointmentService.createAppointent(appointment));
                } else {
                    apiResp.put("header", "Error");
                    apiResp.put("userTrouble", "dokter");
                    apiResp.put("body", appointmentService.checkJadwalDokter(appt.getWaktuAwal(), dokter));
                }

            } else{
                apiResp.put("header", "Error");
                apiResp.put("userTrouble", "pasien");
                apiResp.put("body", appointmentService.checkJadwalPasien(appt.getWaktuAwal(), pasien));
            }
            return apiResp;

        }

    }

    @GetMapping("pasien-view-all")
    private List<AppointmentDTO> viewAllAppointmentPasien(@RequestHeader("Authorization") String token){
        Map<String, String> decodedToken = decode(token);
        System.out.println(decodedToken);
        PasienModel pasien = pasienService.getPasienByUsername(decodedToken.get("USERNAME"));
        List<AppointmentModel> listAppointmentPasien = pasien.getAppointmentPasien();
        List<AppointmentDTO> listAppointmentDTO = new ArrayList<>();
        for (AppointmentModel appt : listAppointmentPasien){
            AppointmentDTO appointmentDTO = new AppointmentDTO();
            appointmentDTO.setPasienName(pasien.getNama());
            appointmentDTO.setDokterName(appt.getDokterModel().getNama());
            appointmentDTO.setWaktuAwal(appt.getWaktuAwal());
            appointmentDTO.setDone(appt.isDone());
            listAppointmentDTO.add(appointmentDTO);
        }
        return listAppointmentDTO;
    }

    private Map<String, String> decode(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Gson gson = new Gson();
        Map<String, String> decodedToken = gson.fromJson(payload, new TypeToken<Map<String, String>>() {}.getType());
        return decodedToken;
    }

}
