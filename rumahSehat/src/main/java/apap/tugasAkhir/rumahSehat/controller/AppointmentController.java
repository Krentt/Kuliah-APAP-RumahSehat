package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.service.AppointmentService;
import apap.tugasAkhir.rumahSehat.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DokterService dokterService;

    // Melihat semua daftar appointment (untuk admin)
    @GetMapping("view-all")
    public String viewAllAppointment(Model model){
        List<AppointmentModel> appointmentModelList = appointmentService.getListAppointment();
        model.addAttribute("listAppointment",appointmentModelList);
        return "appointment/view-all-appointment";
    }

    // Melihat daftar appointment pasien (untuk dokter)
    @GetMapping("dokter-view-all")
    public String viewPasienAppointment(Principal principal, Model model){
        DokterModel dokter = dokterService.getDokterByUsername(principal.getName());
        List<AppointmentModel> listApptDokter = dokter.getAppointmentDokter();
        model.addAttribute("listAppointment",listApptDokter);
        return "appointment/view-all-appointment-dokter";
    }

    @GetMapping("/admin/{kode}")
    public String viewDetailAppointmentAdmin(@PathVariable(value = "kode") String kode, Model model){
        AppointmentModel appt = appointmentService.getAppointmentByKode(kode);
        DokterModel dokter = appt.getDokterModel();
        PasienModel pasien = appt.getPasienModel();
        model.addAttribute("dokter", dokter);
        model.addAttribute("pasien", pasien);
        model.addAttribute("appointment", appt);
        return "appointment/view-detail";
    }
}