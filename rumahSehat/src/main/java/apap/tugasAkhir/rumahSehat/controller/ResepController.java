package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.*;
import apap.tugasAkhir.rumahSehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    TagihanService tagihanService;

    @Autowired
    ApotekerService apotekerService;

    String strresep = "resep";
    String strlistobatexisting = "listObatExisting";

    String urlresepformaddresep = "resep/form-add-resep";

    @GetMapping({"/add/{kodeAppointment}"})
    public String addResepFormPage(Model model, @PathVariable String kodeAppointment) {
        var resep = new ResepModel();
        List <ObatModel> listObat = obatService.getListObat();

        // resep ke appointment
        AppointmentModel appointment = appointmentService.getAppointmentByKode(kodeAppointment);
        resep.setAppointment(appointment);

        List<JumlahModel> listJumlahModelNew = new ArrayList<>();

        resep.setListJumlahModel(listJumlahModelNew);
        resep.getListJumlahModel().add(new JumlahModel());

        model.addAttribute(strresep, resep);
        model.addAttribute(strlistobatexisting, listObat);
        return urlresepformaddresep;
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
        for (JumlahModel jumlahModel : listJumlahModel){
            ObatModel obat = obatService.getObatById(jumlahModel.getObat().getIdObat());
            jumlahModel.setId((long)jumlahObatService.getListJumlahObat().size() + 1);
            jumlahModel.setResep(resep);
            jumlahModel.setObat(obat);

            if(obat.getListJumlahModel() == null || obat.getListJumlahModel().isEmpty()){
                obat.setListJumlahModel(new ArrayList<>());
            }

            // resep ke jumlah
            resep.getListJumlahModel().add(jumlahModel);
            // obat ke jumlah
            obat.getListJumlahModel().add(jumlahModel);

            obatService.addObat(obat);

        }

        return "resep/add-resep-berhasil";
    }

    @PostMapping(value="/add", params={"addRow"})
    public String addRowObatMultiple(
            @ModelAttribute ResepModel resep,
            Model model
    ){
        if (resep.getListJumlahModel() == null || resep.getListJumlahModel().isEmpty()){
            resep.setListJumlahModel(new ArrayList<>());
        }
        resep.getListJumlahModel().add(new JumlahModel());
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute(strresep, resep);
        model.addAttribute(strlistobatexisting, listObat);

        return urlresepformaddresep;
    }

    @PostMapping(value = "/add", params = {"deleteRow"})
    public String deleteRowObatMultiple(
            @ModelAttribute ResepModel resep,
            @RequestParam("deleteRow") Integer row,
            Model model
    ) {
        resep.getListJumlahModel().remove(row.intValue());

        List<ObatModel> listObat = obatService.getListObat();

        model.addAttribute(strresep, resep);
        model.addAttribute(strlistobatexisting, listObat);

        return urlresepformaddresep;
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

        var bisaKonfirmasi = true;
        if (!resep.getIsDone()) {
            for (JumlahModel jumlahModel : listJumlahModel) {
                var obatModel = jumlahModel.getObat();
                if (jumlahModel.getKuantitas() > obatModel.getStok()) {
                    bisaKonfirmasi = false;
                    break;
                }
            }

        }
        else {
            bisaKonfirmasi = false;
        }

        model.addAttribute(strresep, resep);
        model.addAttribute("appointment", appointment);
        model.addAttribute("listJumlahModel", listJumlahModel);
        model.addAttribute("canConfirm", bisaKonfirmasi);

        return "resep/view-detail-resep";
    }


    @GetMapping("/konfirmasi/{idResep}")
    public String confirmResep(@PathVariable("idResep") Long id, Model model) {
        ResepModel resep = resepService.getResepByIdResep(id);
        AppointmentModel appointment = resep.getAppointment();
        List<JumlahModel> listJumlahModel = resep.getListJumlahModel();

        var hargaResep = 0;
        for (JumlahModel jumlahModel : listJumlahModel) {
            jumlahModel.getObat().setStok(jumlahModel.getObat().getStok() - jumlahModel.getKuantitas());
            hargaResep += (jumlahModel.getObat().getHarga() * jumlahModel.getKuantitas());
        }

        var tagihan = new TagihanModel();
        tagihan.setTanggalTerbuat(LocalDateTime.now());
        tagihan.setAppointmentModel(appointment);
        tagihan.setIsPaid(false);
        tagihan.setTotal(appointment.getDokterModel().getTarifDokter() + hargaResep);
        tagihanService.addTagihan(tagihan);


        appointment.setDone(true);
        appointmentService.createAppointent(appointment);

        var auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        ApotekerModel apoteker = apotekerService.getApotekerByUsername(username);

        resep.setApotekerModel(apoteker);
        resep.setIsDone(true);
        resepService.addResep(resep);
        return "redirect:/resep/" + resep.getId();
    }

}
