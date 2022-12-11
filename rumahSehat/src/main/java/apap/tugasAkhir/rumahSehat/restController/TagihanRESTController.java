package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.JumlahModel;
import apap.tugasAkhir.rumahSehat.model.ObatModel;
import apap.tugasAkhir.rumahSehat.model.PasienModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;
import apap.tugasAkhir.rumahSehat.model.TagihanModel;
import apap.tugasAkhir.rumahSehat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    /**
     * Cisco:Feat16 (Melihat Daftar Tagihan pasien)
     * TODO: Add Pasien from Current Session Token
     * TODO: Functions, HTML
     */
    @GetMapping(value = "/list")
    private List<TagihanModel> viewListTagihan(
            Model model) {
        List<TagihanModel> tagihanModelList = new ArrayList<>();

        return tagihanModelList;
    }


    /**
     * Cisco:Feat17 (Melihat Detail Tagihan Pasien)
     * TODO: Add Pasien from Current Session Token
     * TODO: Change Dummy Functions into Smart Functions
     * TODO: Add function membayar (post)
     * {id} adalah ID dari tagihan (BILL-x)
     */
    @GetMapping(value = "/{id}")
    private TagihanModel viewTagihanDetail(
            @PathVariable String id,
            Model model) {
        List<JumlahModel> jml = new ArrayList<>();
        List<JumlahModel> jml2 = new ArrayList<>();
        ResepModel resepModel = new ResepModel(1L, false, LocalDateTime.now(), jml, null, null); //ToDo: Inget ada relasi appointment

        ObatModel obatModel = new ObatModel("1", "Pfi", 100, 100, jml2);

        JumlahModel empOne = new JumlahModel(1L, obatModel, resepModel, 1);

        resepModel.getListJumlahModel().add(empOne);

        //Setters for tagihan Model
        TagihanModel tagihanModel = new TagihanModel();
        tagihanModel.setId("BILL-1");
        tagihanModel.setTanggalTerbuat(LocalDateTime.now());
        tagihanModel.setTanggalBayar(LocalDateTime.now());
        tagihanModel.setIsPaid(false);
//        tagihanModel.setResep(resepModel); // TODO: bikin error
//        tagihanModel.setTarifDokter(100);
//        tagihanModel.calculateTotal();
//        tagihanModel.setKode_appointment("APT-1");

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
        ResepModel resepModel = new ResepModel(id, false, LocalDateTime.now(), jml, null, null); //ToDo: Inget ada relasi appointment

        ObatModel obatModel = new ObatModel();

        JumlahModel empOne = new JumlahModel(1L, obatModel, resepModel, 1);

        resepModel.getListJumlahModel().add(empOne);
        return resepModel;
    }
}
