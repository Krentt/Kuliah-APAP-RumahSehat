package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.restmodel.ObatDTO;
import apap.tugasakhir.rumahsehat.service.ObatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/obat")
public class ObatController {
    @Autowired
    ObatService obatService;

    @GetMapping(value = "/view-obat")
    public String viewAllObat(Model model){
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("listObat", listObat);
        log.info("access daftar obat");
        return "obat/view-all-obat";
    }

    @GetMapping("/{idObat}/update")
    public String updateObatFormPage(@PathVariable String idObat, Model model) {
        ObatModel obat = obatService.getObatById(idObat);
        model.addAttribute("obat", obat);
        log.info("access ubah obat");
        return "obat/form-update-obat";
    }

    @PostMapping(value = "/{idObat}/update")
    public String updateObatSubmitPage(@ModelAttribute ObatDTO obat, Model model) {
        var obatModel = new ObatModel();
        obatModel.setIdObat(obat.getIdObat());
        obatModel.setNamaObat(obat.getNamaObat());
        obatModel.setStok(obat.getStok());
        obatModel.setHarga(obat.getHarga());
        obatModel.setListJumlahModel(obat.getListJumlahModel());

        ObatModel updatedObat= obatService.updateObat(obatModel);
        model.addAttribute("idObat", updatedObat.getIdObat());
        log.info("access ubah obat");
        return "obat/update-obat";
    }

}
