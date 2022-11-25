package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.JumlahModel;
import apap.tugasAkhir.rumahSehat.model.ObatModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;
import apap.tugasAkhir.rumahSehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tagihan")
public class TagihanRESTController {
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
     * TODO: Functions, HTML
     */
    @GetMapping(value = "/{id}")
    private ResepModel viewResepJSON(
            @PathVariable Long id,
            Model model){
        List<JumlahModel> jml = new ArrayList<>();
        ResepModel resepModel = new ResepModel(id,false, LocalDateTime.now(), jml);

        ObatModel obatModel = new ObatModel();

        JumlahModel empOne = new JumlahModel(1L,obatModel,resepModel,1);


        resepModel.getListObat().add(empOne);

        return resepModel;
    }

    /** Cisco:Feat15
     * TODO: Functions, HTML
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
     * TODO: Functions, HTML
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
     * TODO: Functions, HTML
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
