package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.JumlahModel;
import apap.tugasAkhir.rumahSehat.model.ObatModel;
import apap.tugasAkhir.rumahSehat.model.ResepModel;
import apap.tugasAkhir.rumahSehat.restModel.ResepDTO;
import apap.tugasAkhir.rumahSehat.service.ResepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resep")
public class ResepRESTController {

    @Autowired
    ResepService resepService;

    @GetMapping("view/{kode}")
    public ResepDTO getResepByKode(@PathVariable Long kode){
        ResepModel resep = resepService.getResepByIdResep(kode);
        var resepDTO = new ResepDTO();
        resepDTO.setId(resep.getId());
        resepDTO.setDone(resep.getIsDone());
        resepDTO.setCreatedAt(resep.getCreatedAt());

        if (resep.getApotekerModel() != null){
            resepDTO.setNamaApoteker(resep.getApotekerModel().getNama());
        }
        List<Map<String, String>> listObat = new ArrayList<>();
        for (JumlahModel jmlModel : resep.getListJumlahModel()){
            ObatModel obat = jmlModel.getObat();
            Map<String, String> mapObat = new HashMap<>();
            mapObat.put("namaObat", obat.getNamaObat());
            mapObat.put("jumlah", jmlModel.getKuantitas().toString());
            listObat.add(mapObat);
        }
        resepDTO.setListObat(listObat);
        resepDTO.setNamaDokter(resep.getAppointment().getDokterModel().getNama());
        resepDTO.setNamaPasien(resep.getAppointment().getPasienModel().getNama());

        return resepDTO;
    }
}
