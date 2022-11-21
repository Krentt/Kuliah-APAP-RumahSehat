package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.ObatModel;
import apap.tugasAkhir.rumahSehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/obat")
public class ObatController {
    @Autowired
    ObatService obatService;

    @GetMapping(value = "/view-obat")
    private String viewAllObat(Model model){
        List<ObatModel> listObat = obatService.getListObat();
        model.addAttribute("listObat", listObat);
        return "obat/view-all-obat";
    }

    @GetMapping("/{idObat}/update")
    public String updateObatFormPage(@PathVariable String idObat, Model model) {
        ObatModel obat = obatService.getObatById(idObat);
        model.addAttribute("obat", obat);
        return "obat/form-update-obat";
    }

    @PostMapping(value = "/{idObat}/update")
    public String updateObatSubmitPage(@ModelAttribute ObatModel obat, Model model) {
        ObatModel updatedObat= obatService.updateObat(obat);
        model.addAttribute("idObat", updatedObat.getIdObat());
        return "obat/update-obat";
    }

}
