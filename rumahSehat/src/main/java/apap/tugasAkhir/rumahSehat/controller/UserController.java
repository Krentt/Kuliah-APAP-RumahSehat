package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.ApotekerModel;
import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.RoleModel;
import apap.tugasAkhir.rumahSehat.service.*;
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
    private String addDokterFormPage(Model model){
        DokterModel dokter = new DokterModel();
        RoleModel role = roleService.getById(3L);
        model.addAttribute("dokter", dokter);
        model.addAttribute("role", role);
        return "user/form-add-dokter";
    }

    @PostMapping(value = "/add-dokter")
    private String addDokterSubmit(@ModelAttribute DokterModel user, Model model){
        user.setIsSso(false);
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/user/view-dokter";
    }

    @GetMapping(value = "/add-apoteker")
    private String addApotekerFormPage(Model model){
        ApotekerModel apoteker = new ApotekerModel();
        RoleModel role = roleService.getById(4L);
        model.addAttribute("apoteker", apoteker);
        model.addAttribute("role", role);
        return "user/form-add-apoteker";
    }

    @PostMapping(value = "/add-apoteker")
    private String addApotekerSubmit(@ModelAttribute ApotekerModel user, Model model){
        user.setIsSso(false);
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/user/view-apoteker";
    }

    @GetMapping(value = "/view-dokter")
    private String viewAllDokter(Model model){
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("listDokter", listDokter);
        return "user/view-all-dokter";
    }

    @GetMapping(value = "/view-apoteker")
    private String viewAllApoteker(Model model){
        List<ApotekerModel> listApoteker = apotekerService.getListApoteker();
        model.addAttribute("listApoteker", listApoteker);
        return "user/view-all-apoteker";
    }

    @GetMapping(value = "/view-pasien")
    private String viewAllPasien(Model model){
        List<PasienModel> listPasien = pasienService.getAllPasien();
        model.addAttribute("listPasien", listPasien);
        return "user/view-all-pasien";
    }

}
