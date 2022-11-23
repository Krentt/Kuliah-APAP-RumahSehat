package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.JumlahModel;
import apap.tugasAkhir.rumahSehat.model.ObatModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;
import apap.tugasAkhir.rumahSehat.service.ResepService;
import apap.tugasAkhir.rumahSehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
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

    @GetMapping({"/add"})
    public String addResepFormPage(Model model) {
        ResepModel resep = new ResepModel();

        List<JumlahModel> listObat= resep.getListObat();
        List<JumlahModel> listObatNew = new ArrayList<JumlahModel>();

        resep.setListObat(listObatNew);
        resep.getListObat().add(new JumlahModel());

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "resep/form-add-resep";
    }

    @PostMapping(value="/add", params = {"save"})
    public String addResepSubmit(@ModelAttribute ResepModel resep, Model model) {
        if (resep.getListObat() != null){
            for (JumlahModel obat : resep.getListObat()){
                obat.setResep(resep);
            }
        }
        resepService.addResep(resep);
        return "resep/add-resep-berhasil";
    }

    @PostMapping(value="/add", params={"addRow"})
    private String addRowObatMultiple(
            @ModelAttribute ResepModel resep,
            Model model
    ){
        if (resep.getListObat() == null || resep.getListObat().size() == 0){
            resep.setListObat(new ArrayList<>());
        }
        resep.getListObat().add(new JumlahModel());
        List<JumlahModel> listObat = resep.getListObat();

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
        resep.getListObat().remove(rowId.intValue());

        List<JumlahModel> listObat = resep.getListObat();

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

}
