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

    @GetMapping("/")
    private String cartHome(Model model){
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime dateAwal = LocalDateTime.of(date.getYear(), 1, 1,0, 0);
        LocalDateTime dateAkhir = LocalDateTime.of(date.getYear(), 12, 1, 0, 0).plusMonths(1).minusMinutes(1);

        List<AppointmentModel> listAppointment = appointmentService.findAllByWaktuAwalInBetween(dateAwal, dateAkhir);

        model.addAttribute("dataPendapatan", chartService.getDataPendapatan(listAppointment));
        model.addAttribute("tahun",date.getYear());
        log.info("access chart");
        return "chart/view-option";
    }

    @GetMapping("/bulanan-line")
    private String addMonthLineChart(Model model){
        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setMethod("MONTH");
        chartDTO.setDokterModelList(new ArrayList<>());
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("chart", chartDTO);
        model.addAttribute("listDokter", listDokter);
        log.info("access chart bulan");
        return "chart/add-dokter-line";
    }

    @GetMapping("/tahunan-line")
    private String addYearLineChart(Model model){
        ChartDTO chartDTO = new ChartDTO();
        chartDTO.setMethod("YEAR");
        chartDTO.setDokterModelList(new ArrayList<>());
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("chart", chartDTO);
        model.addAttribute("listDokter", listDokter);
        log.info("access chart tahun");
        return "chart/add-dokter-line-tahun";
    }

    @PostMapping(value = "/apptLine-bulan", params = {"addRow"})
    private String addRowDokterLine(
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
        log.info("access add row bulan");

        return "chart/add-dokter-line";
    }

    @PostMapping(value = "/apptLine-bulan", params = {"deleteRow"})
    private String deleteRowDokterLine(
            @ModelAttribute ChartDTO chart,
            @RequestParam("deleteRowLine") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);

        chart.getDokterModelList().remove(rowId.intValue());

        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute("chart", chart);
        model.addAttribute("listDokter", listDokter);
        log.info("access delete row bulan");

        return "chart/add-dokter-line";
    }

    @PostMapping(value = "/apptLine-bulan", params={"save"})
    private String submitDokterLine(
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

        LocalDateTime awal = LocalDateTime.of(chart.getYearMonth().getYear(), chart.getYearMonth().getMonthValue(), 1, 0, 0);
        LocalDateTime akhir = awal.plusMonths(1).minusMinutes(1);

        int day = awal.plusMonths(1).minusMinutes(1).getDayOfMonth();
        int[] days = new int[akhir.getDayOfMonth()];
        for(int i=0; i<day;i++){
            days[i] = i+1;
        }
        model.addAttribute("label",days);
        model.addAttribute("periode",chart.getYearMonth().getMonth());
        model.addAttribute("listData", listData);
        model.addAttribute("listDokter", listNamaDokter);

        return "chart/total-appt-line";
    }

    @PostMapping(value = "/apptLine-tahun", params = {"addRow"})
    private String addRowDokterLine2(
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
        log.info("access add row chart tahun");
        return "chart/add-dokter-line-tahun";
    }

    @PostMapping(value = "/apptLine-tahun", params = {"deleteRow"})
    private String deleteRowDokterLine2(
            @ModelAttribute ChartDTO chart,
            @RequestParam("deleteRowLine") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);

        chart.getDokterModelList().remove(rowId.intValue());

        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute("chart", chart);
        model.addAttribute("listDokter", listDokter);
        log.info("access delete row chart tahun");

        return "chart/add-dokter-line-tahun";
    }

    @PostMapping(value = "/apptLine-tahun", params={"save"})
    private String submitDokterLine2(
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
        String[] month = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Okt", "Nov", "Des"};
        model.addAttribute("label",month);
        model.addAttribute("periode",chart.getYear());
        model.addAttribute("listData", listData);
        model.addAttribute("listDokter", listNamaDokter);
        log.info("access save line chart tahun");
        return "chart/total-appt-line";
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
