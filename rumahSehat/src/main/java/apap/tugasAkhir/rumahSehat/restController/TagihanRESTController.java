package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.*;
import apap.tugasAkhir.rumahSehat.service.*;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@XSlf4j
@RestController
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
    @Autowired
    private TagihanService tagihanService;


    /**
     * Cisco:Feat16 (Melihat Daftar Tagihan pasien)
     * {username} of pasien
     * TODO: Add Pasien from Current Session Token
     * TODO: Functions, HTML
     * TODO: Action Logging
     */
    @GetMapping(value = "/{username}/list")
    private List<TagihanModel> viewListTagihan(
            @PathVariable String username,
            Model model) {
        //Find PasienModel by Username
        PasienModel pasienModel = pasienService.getPasienByUsername(username);

        List<TagihanModel> tagihanModelList = new ArrayList<>(tagihanService.getListTagihanByPasien(pasienModel));

        model.addAttribute("username", username);

        return tagihanModelList;
    }


    /**
     * Cisco:Feat17 (Melihat Detail Tagihan Pasien)
     * TODO: Change Dummy Functions into Smart Functions
     * TODO: Add function membayar (post)
     * TODO: Action Logging
     * {id} adalah ID dari tagihan (BILL-x)
     */
    @GetMapping(value = "/{id}")
    private TagihanModel viewTagihanDetail(
            @PathVariable String id,
            Model model) {

        //Dummy Data
//        List<JumlahModel> jml = new ArrayList<>();
//        List<JumlahModel> jml2 = new ArrayList<>();
//        ResepModel resepModel = new ResepModel(1L, false, LocalDateTime.now(), jml);
//
//        ObatModel obatModel = new ObatModel("1", "Pfi", 100, 100, jml2);
//
//        JumlahModel empOne = new JumlahModel(1L, obatModel, resepModel, 1);
//
//        resepModel.getListJumlahModel().add(empOne);

        TagihanModel tagihanModel = new TagihanModel(); //TODO: inline when cleared to delete dummy data
        tagihanModel = tagihanService.getTagihanById(id);

        // Dummy Data
//        AppointmentModel appointmentModel = new AppointmentModel();
//
//        tagihanModel.setAppointmentModel(appointmentModel);
//        tagihanModel.setId("BILL-1");
//        tagihanModel.setTanggalTerbuat(appointmentModel.getWaktu_awal());
//        tagihanModel.setTanggalBayar(LocalDateTime.now());
//        tagihanModel.setIsPaid(false);
//
//        tagihanModel.calculateTotal();


        return tagihanModel;
    }

    /**
     * Cisco:View Resep (kedobelan sama no 11)
     * {id} adalah ID dari Resep
     */
    @GetMapping(value = "/resep/{id}")
    private ResepModel viewResepJSON(
            @PathVariable Long id,
            Model model) {
        List<JumlahModel> jml = new ArrayList<>();
        ResepModel resepModel = new ResepModel(id, false, LocalDateTime.now(), jml, null); //ToDo: Inget ada relasi appointment

        ObatModel obatModel = new ObatModel();

        JumlahModel empOne = new JumlahModel(1L, obatModel, resepModel, 1);

        resepModel.getListJumlahModel().add(empOne);
        return resepModel;
    }
}
