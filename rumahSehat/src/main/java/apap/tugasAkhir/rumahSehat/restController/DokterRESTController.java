package apap.tugasAkhir.rumahSehat.restController;

import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dokter")
public class DokterRESTController {


    @Autowired
    DokterService dokterService;

    @GetMapping("/get-all")
    private List<DokterModel> viewAllDokter(){
        return dokterService.getListDokter();
    }
}
