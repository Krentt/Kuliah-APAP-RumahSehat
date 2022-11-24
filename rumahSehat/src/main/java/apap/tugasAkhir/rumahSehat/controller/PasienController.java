package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.*;
import apap.tugasAkhir.rumahSehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pasien")
public class PasienController {
    @Autowired
    private UserService userService;
    @Autowired
    private DokterService dokterService;
    @Autowired
    private ApotekerService apotekerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasienService pasienService;

    /** Cisco:Feat14
     * TODO
     */
    @GetMapping(value = "/profile/{id}")
    private String viewPasienProfile(
            @PathVariable String id,
            Model model){
        PasienModel pasien = pasienService.getPasienById(id);
        model.addAttribute("pasien", pasien);
        return "pasien/profile";
    }

    /** Cisco:Feat15
     * TODO
     */
    @GetMapping(value = "/saldo/{id}")
    private String viewPasienSaldo(
            @PathVariable String id,
            Model model){
        PasienModel pasien = pasienService.getPasienById(id);
        model.addAttribute("pasien", pasien);
        return "pasien/saldo";
    }

    /** Cisco:Feat16
     * TODO
     */
    @GetMapping(value = "/{id}/tagihan")
    private String viewPasienTagihan(
            @PathVariable String id,
            Model model){
        PasienModel pasien = pasienService.getPasienById(id);
        model.addAttribute("pasien", pasien);
        return "pasien/tagihanList";
    }

    /** Cisco:Feat17
     * TODO
     */
    @GetMapping(value = "/{id}/tagihan/{idTagihan}")
    private String viewTagihan(
            @PathVariable String id,
            @PathVariable String idTagihan,
            Model model){
        PasienModel pasien = pasienService.getPasienById(id);
        model.addAttribute("pasien", pasien);
        return "pasien/tagihan";
    }
}
