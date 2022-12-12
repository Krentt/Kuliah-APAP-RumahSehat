package apap.tugasAkhir.rumahSehat.controller;

import apap.tugasAkhir.rumahSehat.model.AppointmentModel;
import apap.tugasAkhir.rumahSehat.model.DokterModel;
import apap.tugasAkhir.rumahSehat.restModel.ChartDTO;
import apap.tugasAkhir.rumahSehat.service.AppointmentService;
import apap.tugasAkhir.rumahSehat.service.ChartService;
import apap.tugasAkhir.rumahSehat.service.DokterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/chart")
public class ChartController {
    @Autowired
    DokterService dokterService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ChartService chartService;

    String strchart = "chart";
    String strlistdokter = "listDokter";
    String strlabel = "label";
    String strlistdata = "listData";

    String urlchartadddokterbar = "chart/add-dokter-bar";
    String urlchartadddokterline = "chart/add-dokter-line";
    String urlchartadddokterlinetahun = "chart/add-dokter-line-tahun";


    @GetMapping("/")
    public String cartHome(Model model){
        var date = LocalDateTime.now();
        var dateAwal = LocalDateTime.of(date.getYear(), 1, 1,0, 0);
        var dateAkhir = LocalDateTime.of(date.getYear(), 12, 1, 0, 0).plusMonths(1).minusMinutes(1);

        List<AppointmentModel> listAppointment = appointmentService.findAllByWaktuAwalInBetween(dateAwal, dateAkhir);

        model.addAttribute("dataPendapatan", chartService.getDataPendapatan(listAppointment));
        model.addAttribute("tahun",date.getYear());
        log.info("access chart");
        return "chart/view-option";
    }

    @GetMapping("/bulanan-line")
    public String addMonthLineChart(Model model){
        var chartDTO = new ChartDTO();
        chartDTO.setMethod("MONTH");
        chartDTO.setDokterModelList(new ArrayList<>());
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute(strchart, chartDTO);
        model.addAttribute(strlistdokter, listDokter);
        log.info("access chart bulan");
        return urlchartadddokterline;
    }

    @GetMapping("/tahunan-line")
    public String addYearLineChart(Model model){
        var chartDTO = new ChartDTO();
        chartDTO.setMethod("YEAR");
        chartDTO.setDokterModelList(new ArrayList<>());
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute(strchart, chartDTO);
        model.addAttribute(strlistdokter, listDokter);
        log.info("access chart tahun");
        return urlchartadddokterlinetahun;
    }

    @PostMapping(value = "/apptLine-bulan", params = {"addRow"})
    public String addRowDokterLine(
            @ModelAttribute ChartDTO chart,
            Model model
    ){
        if(chart.getDokterModelList()==null){
            chart.setDokterModelList(new ArrayList<>());
        }

        chart.getDokterModelList().add(new DokterModel());
        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute(strchart, chart);
        model.addAttribute(strlistdokter, listDokter);
        log.info("access add row bulan");

        return urlchartadddokterline;
    }

    @PostMapping(value = "/apptLine-bulan", params = {"deleteRow"})
    public String deleteRowDokterLine(
            @ModelAttribute ChartDTO chart,
            @RequestParam("deleteRowLine") Integer row,
            Model model
    ){

        chart.getDokterModelList().remove(row.intValue());

        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute(strchart, chart);
        model.addAttribute(strlistdokter, listDokter);
        log.info("access delete row bulan");

        return urlchartadddokterline;
    }

    @PostMapping(value = "/apptLine-bulan", params={"save"})
    public String submitDokterLine(
            @ModelAttribute ChartDTO chart,
            Model model
    ){
        if(chart.getDokterModelList() == null){
            chart.setDokterModelList(new ArrayList<>());
        }

        List<DokterModel> listDokterChart = chart.getDokterModelList();
        List<DokterModel> listDokterDb = new ArrayList<>();
        List<String> listNamaDokter = new ArrayList<>();

        for (DokterModel dokter : listDokterChart){
            DokterModel realDokter = dokterService.getDokterByUsername(dokter.getUsername());
            listDokterDb.add(realDokter);
            listNamaDokter.add(realDokter.getNama());
        }

        List<int[]> listData = chartService.getDataLineBulan(chart.getYearMonth().getYear(),chart.getYearMonth().getMonthValue(), listDokterDb);

        log.info("access submit chart bulan");

        var awal = LocalDateTime.of(chart.getYearMonth().getYear(), chart.getYearMonth().getMonthValue(), 1, 0, 0);
        LocalDateTime akhir = awal.plusMonths(1).minusMinutes(1);

        int day = awal.plusMonths(1).minusMinutes(1).getDayOfMonth();
        var days = new int[akhir.getDayOfMonth()];
        for(var i=0; i<day;i++){
            days[i] = i+1;
        }
        model.addAttribute(strlabel,days);
        model.addAttribute("periode",chart.getYearMonth().getMonth());
        model.addAttribute(strlistdata, listData);
        model.addAttribute(strlistdokter, listNamaDokter);

        return "chart/total-appt-line";
    }

    @PostMapping(value = "/apptLine-tahun", params = {"addRow"})
    public String addRowDokterLine2(
            @ModelAttribute ChartDTO chart,
            Model model
    ){
        if(chart.getDokterModelList()==null){
            chart.setDokterModelList(new ArrayList<>());
        }

        chart.getDokterModelList().add(new DokterModel());
        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute(strchart, chart);
        model.addAttribute(strlistdokter, listDokter);
        log.info("access add row chart tahun");
        return urlchartadddokterlinetahun;
    }

    @PostMapping(value = "/apptLine-tahun", params = {"deleteRow"})
    public String deleteRowDokterLine2(
            @ModelAttribute ChartDTO chart,
            @RequestParam("deleteRowLine") Integer row,
            Model model
    ){

        chart.getDokterModelList().remove(row.intValue());

        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute(strchart, chart);
        model.addAttribute(strlistdokter, listDokter);
        log.info("access delete row chart tahun");

        return urlchartadddokterlinetahun;
    }

    @PostMapping(value = "/apptLine-tahun", params={"save"})
    public String submitDokterLine2(
            @ModelAttribute ChartDTO chart,
            Model model
    ){
        if(chart.getDokterModelList() == null){
            chart.setDokterModelList(new ArrayList<>());
        }

        List<DokterModel> listDokterChart = chart.getDokterModelList();
        List<DokterModel> listDokterDb = new ArrayList<>();
        List<String> listNamaDokter = new ArrayList<>();

        for (DokterModel dokter : listDokterChart){
            DokterModel realDokter = dokterService.getDokterByUsername(dokter.getUsername());
            listDokterDb.add(realDokter);
            listNamaDokter.add(realDokter.getNama());
        }

        List<int[]> listData = chartService.getDataLineTahun(chart.getYear(), listDokterDb);
        var month = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Okt", "Nov", "Des"};
        model.addAttribute(strlabel,month);
        model.addAttribute("periode",chart.getYear());
        model.addAttribute(strlistdata, listData);
        model.addAttribute(strlistdokter, listNamaDokter);
        log.info("access save line chart tahun");
        return "chart/total-appt-line";
    }


    @GetMapping("/quantity-bar")
    public String addBarChart(Model model){
        var chartDTO = new ChartDTO();
        chartDTO.setMethod("APPT");
        chartDTO.setDokterModelList(new ArrayList<>());
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute(strchart, chartDTO);
        model.addAttribute(strlistdokter, listDokter);
        return urlchartadddokterbar;
    }

    @GetMapping("/income-bar")
    public String addIncomeBarChart(Model model){
        var chartDTO = new ChartDTO();
        chartDTO.setMethod("INCOME");
        chartDTO.setDokterModelList(new ArrayList<>());
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute(strchart, chartDTO);
        model.addAttribute(strlistdokter, listDokter);
        return urlchartadddokterbar;
    }


    @PostMapping(value = "/appointment-bar", params = {"addRow"})
    public String addRowDokter(
            @ModelAttribute ChartDTO chart,
            Model model
    ){
        if(chart.getDokterModelList()==null){
           chart.setDokterModelList(new ArrayList<>());
        }

        chart.getDokterModelList().add(new DokterModel());
        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute(strchart, chart);
        model.addAttribute(strlistdokter, listDokter);

        return urlchartadddokterbar;
    }

    @PostMapping(value = "/appointment-bar", params = {"deleteRow"})
    public String deleteRowDokter(
            @ModelAttribute ChartDTO chart,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){

        chart.getDokterModelList().remove(row.intValue());

        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute(strchart, chart);
        model.addAttribute(strlistdokter, listDokter);

        return urlchartadddokterbar;
    }

    @PostMapping(value = "/appointment-bar", params={"save"})
    public String submitDokter(
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
                var count = (long) dokter.getAppointmentDokter().size();
                listData.add(count);
            }
            model.addAttribute(strlabel, "Total Kuantitas Appointment");
            model.addAttribute("ylabel", "Jumlah Appointment");
        } else {
            for (DokterModel dokter : listDokter){
                var income = 0L;
                if (dokter.getAppointmentDokter() != null){
                    income = ((long) dokter.getAppointmentDokter().size() * dokter.getTarifDokter());
                }
                listData.add(income);
            }
            model.addAttribute(strlabel, "Total Pendapatan Dokter");
            model.addAttribute("ylabel", "Pendapatan (Rp)");
        }


        model.addAttribute(strlistdata, listData);
        model.addAttribute("listNamaDokter", listNamaDokter);
        return "chart/total-appt-bar";
    }

}
