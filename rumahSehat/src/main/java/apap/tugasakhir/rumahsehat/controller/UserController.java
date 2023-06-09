package apap.tugasakhir.rumahsehat.controller;

import apap.tugasakhir.rumahsehat.model.ApotekerModel;
import apap.tugasakhir.rumahsehat.model.DokterModel;
import apap.tugasakhir.rumahsehat.model.PasienModel;
import apap.tugasakhir.rumahsehat.model.RoleModel;
import apap.tugasakhir.rumahsehat.restmodel.ApotekerDTO;
import apap.tugasakhir.rumahsehat.restmodel.DokterDTO;
import apap.tugasakhir.rumahsehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DokterService dokterService;
    @Autowired
    private ApotekerService apotekerService;
    @Autowired
    private PasienService pasienService;
    @Autowired
    private RoleService roleService;



    @GetMapping(value = "/add-dokter")
    public String addDokterFormPage(Model model){
        var dokter = new DokterModel();
        RoleModel role = roleService.getById(3L);
        model.addAttribute("dokter", dokter);
        model.addAttribute("role", role);
        return "user/form-add-dokter";
    }

    @PostMapping(value = "/add-dokter")
    public String addDokterSubmit(@ModelAttribute DokterDTO dokterDTO, Model model){
        var user = dokterDTO.toUserModel();
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/user/view-dokter";
    }

    @GetMapping(value = "/add-apoteker")
    public String addApotekerFormPage(Model model){
        var apoteker = new ApotekerModel();
        RoleModel role = roleService.getById(4L);
        model.addAttribute("apoteker", apoteker);
        model.addAttribute("role", role);
        return "user/form-add-apoteker";
    }

    @PostMapping(value = "/add-apoteker")
    public String addApotekerSubmit(@ModelAttribute ApotekerDTO apotekerDTO, Model model){
        var user = apotekerDTO.toUserModel();
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/user/view-apoteker";
    }

    @GetMapping(value = "/view-dokter")
    public String viewAllDokter(Model model){
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("listDokter", listDokter);
        return "user/view-all-dokter";
    }

    @GetMapping(value = "/view-apoteker")
    public String viewAllApoteker(Model model){
        List<ApotekerModel> listApoteker = apotekerService.getListApoteker();
        model.addAttribute("listApoteker", listApoteker);
        return "user/view-all-apoteker";
    }

    @GetMapping(value = "/view-pasien")
    public String viewAllPasien(Model model){
        List<PasienModel> listPasien = pasienService.getAllPasien();
        model.addAttribute("listPasien", listPasien);
        return "user/view-all-pasien";
    }

}
