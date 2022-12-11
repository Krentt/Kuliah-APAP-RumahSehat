package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.restModel.ChartDTO;
import apap.tugasAkhir.rumahSehat.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chart")
public class ChartController {
    @Autowired
    DokterService dokterService;

    @GetMapping("/")
    private String cartHome(){
        return "chart/view-option";
    }

    @GetMapping("/quantity-bar")
    private String addBarChart(Model model){
        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setMethod("APPT");
        chartDTO.setDokterModelList(new ArrayList<>());
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("chart", chartDTO);
        model.addAttribute("listDokter", listDokter);
        return "chart/add-dokter-bar";
    }

    @GetMapping("/income-bar")
    private String addIncomeBarChart(Model model){
        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setMethod("INCOME");
        chartDTO.setDokterModelList(new ArrayList<>());
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("chart", chartDTO);
        model.addAttribute("listDokter", listDokter);
        return "chart/add-dokter-bar";
    }

    @PostMapping(value = "/appointment-bar", params = {"addRow"})
    private String addRowDokter(
            @ModelAttribute ChartDTO chart,
            Model model
    ){
        if(chart.getDokterModelList()==null){
           chart.setDokterModelList(new ArrayList<>());
        }

        chart.getDokterModelList().add(new DokterModel());
        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute("chart", chart);
        model.addAttribute("listDokter", listDokter);

        return "chart/add-dokter-bar";
    }

    @PostMapping(value = "/appointment-bar", params = {"deleteRow"})
    private String deleteRowDokter(
            @ModelAttribute ChartDTO chart,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);

        chart.getDokterModelList().remove(rowId.intValue());

        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute("chart", chart);
        model.addAttribute("listDokter", listDokter);

        return "chart/add-dokter-bar";
    }

    @PostMapping(value = "/appointment-bar", params={"save"})
    private String submitDokter(
            @ModelAttribute ChartDTO chart,
            Model model
    ){
        if(chart.getDokterModelList() == null){
            chart.setDokterModelList(new ArrayList<>());
        }

        List<DokterModel> listDokterChart = chart.getDokterModelList();
        List<DokterModel> listDokter = new ArrayList<>();

        for (DokterModel dokter : listDokterChart){
            DokterModel realDokter = dokterService.getDokterByUsername(dokter.getUsername());
            if (!listDokter.contains(realDokter)){
                listDokter.add(realDokter);
            }
        }

        List<String> listNamaDokter = new ArrayList<>();

        for (DokterModel dokter : listDokter){
            listNamaDokter.add(dokter.getNama());
        }

        List<Long> listData = new ArrayList<>();
        if (chart.getMethod().equals("APPT")){
            for (DokterModel dokter : listDokter){
                Long count = Long.valueOf(dokter.getAppointmentDokter().size());
                listData.add(count);
            }
            model.addAttribute("label", "Total Kuantitas Appointment");
            model.addAttribute("ylabel", "Jumlah Appointment");
        } else {
            for (DokterModel dokter : listDokter){
                Long income = 0L;
                if (dokter.getAppointmentDokter() != null){
                    income = Long.valueOf(dokter.getAppointmentDokter().size()*dokter.getTarifDokter());
                }
                listData.add(income);
            }
            model.addAttribute("label", "Total Pendapatan Dokter");
            model.addAttribute("ylabel", "Pendapatan (Rp)");
        }


        model.addAttribute("listData", listData);
        model.addAttribute("listNamaDokter", listNamaDokter);
        return "chart/total-appt-bar";
    }

}
