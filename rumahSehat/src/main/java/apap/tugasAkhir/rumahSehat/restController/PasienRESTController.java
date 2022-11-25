package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.*;
import apap.tugasAkhir.rumahSehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pasien")
public class PasienRESTController {
    @Autowired
    private PasienService pasienService;

    /** Cisco:Feat14 (Melihat Profile Pasien)
     * TODO: Functions, Flutter
     * {username} adalah username dari akun pasien
     */
    @GetMapping(value = "/{Username}/profile")
    private PasienModel viewPasienProfile(
            @PathVariable String Username){
        PasienModel pasienModel = new PasienModel();
//        pasienModel = pasienService.getPasienByUsername(Username)

        //Dummy Data Test Set
        pasienModel.setId("123e4567-e89b-12d3-a456-426614174000");
        pasienModel.setNama("Test");
        pasienModel.setUsername(Username);
        pasienModel.setPassword("ASHDASHD");
        pasienModel.setEmail("test@test.com");
        pasienModel.setIsSso(false);
        pasienModel.setRole(new RoleModel());
        pasienModel.setSaldoPasien(100L);
        pasienModel.setUmurPasien(45);

        return pasienModel;
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * TODO: Functions, Flutter, POST
     * {username} adalah username dari akun pasien
     */
    @GetMapping(value = "{Username}/saldo")
    private PasienModel viewPasienSaldoGet(
            @PathVariable String Username,
            Model model){
        PasienModel pasienModel = new PasienModel();
//        pasienModel = pasienService.getPasienByUsername(Username)

        //Dummy Data Test Set
        pasienModel.setId("123e4567-e89b-12d3-a456-426614174000");
        pasienModel.setNama("Test2");
        pasienModel.setUsername(Username);
        pasienModel.setPassword("ASHDASHD");
        pasienModel.setEmail("test@test.com");
        pasienModel.setIsSso(false);
        pasienModel.setRole(new RoleModel());
        pasienModel.setSaldoPasien(100L);
        pasienModel.setUmurPasien(45);


        model.addAttribute("saldo", pasienModel.getSaldoPasien());

        return pasienModel;
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * TODO: Functions, Flutter, POST
     * {username} adalah username dari akun pasien
     */
    @PostMapping(value = "{Username}/saldo")
    private PasienModel viewPasienSaldoPost(
            @PathVariable String Username,
            Model model){
        PasienModel pasien = pasienService.getPasienByUsername(Username);
        model.addAttribute("pasien", pasien);
        return pasien;
    }
}
