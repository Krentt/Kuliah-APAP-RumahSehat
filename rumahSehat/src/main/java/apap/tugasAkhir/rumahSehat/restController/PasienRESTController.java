package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.*;
import apap.tugasAkhir.rumahSehat.service.*;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@XSlf4j
@RestController
@RequestMapping("/pasien")
public class PasienRESTController {
    @Autowired
    private PasienService pasienService;

    /** Cisco:Feat14 (Melihat Profile Pasien)
     * TODO: Functions, Flutter
     * TODO: Action Logging
     * {username} adalah username dari akun pasien
     */
    @GetMapping(value = "/{Username}/profile")
    private PasienModel viewPasienProfile(
            @PathVariable String Username){
        PasienModel pasienModel = pasienService.getPasienByUsername(Username);

        pasienModel.setSaldoPasien(0L); //TODO: Remove this and Inline return function

        //Dummy Data Test Set
//        pasienModel.setId("123e4567-e89b-12d3-a456-426614174000");
//        pasienModel.setNama("Test");
//        pasienModel.setUsername(Username);
//        pasienModel.setPassword("ASHDASHD");
//        pasienModel.setEmail("test@test.com");
//        pasienModel.setIsSso(false);
//        pasienModel.setRole(new RoleModel());
//        pasienModel.setSaldoPasien(100L);
//        pasienModel.setUmurPasien(45);

        return pasienModel;
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * TODO: Functions, Flutter, POST
     * TODO: Action Logging
     * GET Function
     * TODO: Functions, Flutter
     * {username} adalah username dari akun pasien
     */
    @GetMapping(value = "{Username}/saldo")
    private PasienModel viewPasienSaldoGet(
            @PathVariable String Username,
            Model model){
        PasienModel pasienModel = new PasienModel();
        pasienModel = pasienService.getPasienByUsername(Username);

        //Dummy Data Test Set
//        pasienModel.setId("123e4567-e89b-12d3-a456-426614174000");
//        pasienModel.setNama("Test2");
//        pasienModel.setUsername(Username);
//        pasienModel.setPassword("ASHDASHD");
//        pasienModel.setEmail("test@test.com");
//        pasienModel.setIsSso(false);
//        pasienModel.setRole(new RoleModel());
//        pasienModel.setSaldoPasien(100L);
//        pasienModel.setUmurPasien(45);


        model.addAttribute("saldo", pasienModel.getSaldoPasien());

        return pasienModel;
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * POST Function
     * TODO: Functions, Flutter, POST
     * TODO: Action Logging
     * {username} adalah username dari akun pasien
     */
    @PostMapping(value = "{Username}/saldo")
    private PasienModel viewPasienSaldoPost(
            @PathVariable String Username,
            @ModelAttribute PasienModel pasienModel,
            Model model){
        PasienModel pasien = pasienService.getPasienByUsername(Username);
        pasien.setSaldoPasien(pasienModel.getSaldoPasien());
        model.addAttribute("pasien", pasien);
        return pasien;
    }
}
