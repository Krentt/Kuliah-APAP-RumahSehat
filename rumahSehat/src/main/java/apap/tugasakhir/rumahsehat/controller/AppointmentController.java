package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;
import apap.tugasakhir.rumahsehat.service.AppointmentService;
import apap.tugasakhir.rumahsehat.service.DokterService;
import apap.tugasakhir.rumahsehat.service.TagihanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;
    @Autowired
    DokterService dokterService;
    @Autowired
    TagihanService tagihanService;

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

    @GetMapping("/{kode}")
    public String viewDetailAppointmentAdmin(@PathVariable(value = "kode") String kode, Model model){
        var appt = appointmentService.getAppointmentByKode(kode);
        var dokter = appt.getDokterModel();
        var pasien = appt.getPasienModel();
        model.addAttribute("dokter", dokter);
        model.addAttribute("pasien", pasien);
        model.addAttribute("appointment", appt);
        return "appointment/view-detail";
    }

    @GetMapping("/{kode}/selesai")
    public ModelAndView changeStatusAppointment(@PathVariable(value = "kode") String kode, RedirectAttributes redirectAttrs){
        AppointmentModel appt = appointmentService.getAppointmentByKode(kode);

        if (appt.getResepModel() != null) {
            var resep = appt.getResepModel();
            if (!resep.getIsDone()){
                redirectAttrs.addFlashAttribute("error", "Resep belum dikonfirmasi oleh Apoteker!");
                return new ModelAndView("redirect:/appointment/{kode}");
            }
        }
        appt.setDone(true);
        appointmentService.createAppointment(appt);

        // Cisco: Membuat tagihan from appointment (appt)
        tagihanService.createTagihanByAppointment(appt, new TagihanModel());

        redirectAttrs.addFlashAttribute("success", "Selamat! Appointment telah selesai");
        return new ModelAndView("redirect:/appointment/{kode}");

    }
}
