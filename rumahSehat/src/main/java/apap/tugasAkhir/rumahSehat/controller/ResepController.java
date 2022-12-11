package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.*;
import apap.tugasAkhir.rumahSehat.service.AppointmentService;
import apap.tugasAkhir.rumahSehat.service.ResepService;
import apap.tugasAkhir.rumahSehat.service.ObatService;
import apap.tugasAkhir.rumahSehat.service.JumlahObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resep")
public class ResepController {
    @Autowired
    ResepService resepService;

    @Autowired
    ObatService obatService;

    @Autowired
    JumlahObatService jumlahObatService;

    @Autowired
    AppointmentService appointmentService;

    @GetMapping({"/add/{kodeAppointment}"})
    public String addResepFormPage(Model model, @PathVariable String kodeAppointment) {
        ResepModel resep = new ResepModel();
        List <ObatModel> listObat = obatService.getListObat();

        // resep ke appointment
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kodeAppointment);
        resep.setAppointment(appointment);

        List<JumlahModel> listJumlahModelNew = new ArrayList<JumlahModel>();

        resep.setListJumlahModel(listJumlahModelNew);
        resep.getListJumlahModel().add(new JumlahModel());

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "resep/form-add-resep";
    }

    @PostMapping(value="/add", params = {"save"})
    public String addResepSubmit(@ModelAttribute ResepModel resep, Model model) {
       List<JumlahModel> listJumlahModel = resep.getListJumlahModel();
        Long idResep = (long) resepService.getListResep().size() + 1;
        resep.setId(idResep);
        resep.setIsDone(false);
        resep.setCreatedAt(LocalDateTime.now());
        resep.setListJumlahModel(new ArrayList<>());

        AppointmentModel appointmentOfResep = appointmentService.getAppointmentByKode(resep.getAppointment().getKode());
        appointmentOfResep.setResepModel(resep);
        resep.setAppointment(appointmentOfResep);
        resepService.addResep(resep);
//        appointmentService.createAppointent(appointmentOfResep);
        for (JumlahModel jumlahModel : listJumlahModel){
            ObatModel obat = obatService.getObatById(jumlahModel.getObat().getIdObat());
            jumlahModel.setId((long)jumlahObatService.getListJumlahObat().size() + 1);
            jumlahModel.setResep(resep);
            jumlahModel.setObat(obat);

            if(obat.getListJumlahModel() == null || obat.getListJumlahModel().size() == 0){
                obat.setListJumlahModel(new ArrayList<>());
            }

            // resep ke jumlah
            resep.getListJumlahModel().add(jumlahModel);
            // obat ke jumlah
            obat.getListJumlahModel().add(jumlahModel);

            obat.setStok(obat.getStok()-jumlahModel.getKuantitas());
            obatService.addObat(obat);

        }

        return "resep/add-resep-berhasil";
    }

    @PostMapping(value="/add", params={"addRow"})
    private String addRowObatMultiple(
            @ModelAttribute ResepModel resep,
            Model model
    ){
        if (resep.getListJumlahModel() == null || resep.getListJumlahModel().size() == 0){
            resep.setListJumlahModel(new ArrayList<>());
        }
        resep.getListJumlahModel().add(new JumlahModel());
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);

        return "resep/form-add-resep";
    }

    @PostMapping(value = "/add", params = {"deleteRow"})
    private String deleteRowObatMultiple(
            @ModelAttribute ResepModel resep,
            @RequestParam("deleteRow") Integer row,
            Model model
    ) {
        final Integer rowId = Integer.valueOf(row);
        resep.getListJumlahModel().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);

        return "resep/form-add-resep";
    }

    @GetMapping("/viewall")
    public String listResep(Model model){
        List<ResepModel> listResep = resepService.getListResep();
        model.addAttribute("listResep", listResep);
        return "resep/viewall-resep";
    }

    @GetMapping("/{idResep}")
    public String viewDetailResep(@PathVariable(value = "idResep") Long id, Model model){
        ResepModel resep = resepService.getResepByIdResep(id);
        AppointmentModel appointment = resep.getAppointment();
        List<JumlahModel> listJumlahModel = resep.getListJumlahModel();

        model.addAttribute("resep", resep);
        model.addAttribute("appointment", appointment);
        model.addAttribute("listJumlahModel", listJumlahModel);

        return "resep/view-detail-resep";
    }

}
