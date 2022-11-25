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

        return pasienService.getPasienByUsername(Username);
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * TODO: Functions, Flutter, POST
     * {username} adalah username dari akun pasien
     */
    @GetMapping(value = "{Username}/saldo")
    private String viewPasienSaldoGet(
            @PathVariable String Username,
            Model model){
        PasienModel pasien = pasienService.getPasienByUsername(Username);
        model.addAttribute("pasien", pasien);
        return "pasien/saldo";
    }

    /** Cisco:Feat15 (Top Up Saldo Pasien)
     * TODO: Functions, Flutter, POST
     * {username} adalah username dari akun pasien
     */
    @PostMapping(value = "{Username}/saldo")
    private String viewPasienSaldoPost(
            @PathVariable String Username,
            Model model){
        PasienModel pasien = pasienService.getPasienByUsername(Username);
        model.addAttribute("pasien", pasien);
        return "pasien/saldo";
    }
}
