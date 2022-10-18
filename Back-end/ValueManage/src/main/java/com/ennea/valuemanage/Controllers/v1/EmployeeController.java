package com.ennea.valuemanage.Controllers.v1;

import com.ennea.valuemanage.API.v1.DTO.CustomerDTO;
import com.ennea.valuemanage.API.v1.DTO.EmployeeDTO;
import com.ennea.valuemanage.API.v1.DTO.ReportDTO;
import com.ennea.valuemanage.API.v1.Mapper.CustomerMapper;
import com.ennea.valuemanage.Repositories.security.UserRepository;
import com.ennea.valuemanage.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    EmployeeService employeeService;

    UserRepository userRepository;

    public EmployeeController(EmployeeService employeeService,
                              UserRepository userRepository) {
        this.employeeService = employeeService;
        this.userRepository = userRepository;
    }


    private Long findIdByPrinciple(Principal principal){
        return userRepository.findUserByUsername(principal.getName()).get().getEmployeeId();
    }
//
//    @CrossOrigin(origins= "http://localhost:3000")
    @GetMapping({"/retailers","/distributors"})
    public ResponseEntity<Page<CustomerDTO>> getCustomers(Principal principal,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size
                                                          ){

        return new ResponseEntity<>(employeeService.getCustomers(findIdByPrinciple(principal), PageRequest.of(page,size)), HttpStatus.OK);
    }

    @PostMapping({"/retailers","/distributors"})
    public ResponseEntity<Void> addNewCustomer(Principal principal,@RequestBody CustomerDTO customerDTO){
        employeeService.saveCustomer(findIdByPrinciple(principal),customerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping({"/attendance"})
    public ResponseEntity<Page<LocalDate>> getPresentDates(Principal principal, @RequestParam(name="month",defaultValue = "-1") int month, @RequestParam(name="year",defaultValue = "-1") int year){

        if(month<0)
            month=LocalDate.now().getMonthValue();
        if(year<0)
            year=LocalDate.now().getYear();
        return new ResponseEntity<>(employeeService.getPresentDates(findIdByPrinciple(principal),month,year),HttpStatus.OK);

    }

    @PostMapping("/attendance")
    public ResponseEntity<String> markAttendance(Principal principal){
        employeeService.markAttendance(findIdByPrinciple(principal));
        return new ResponseEntity<>("Attendance Marked",HttpStatus.CREATED);
    }


    @PostMapping( "/reports") //for themselves
    public ResponseEntity<String> submitReport(Principal principal,@RequestBody ReportDTO reportDTO){
        employeeService.submitReport(findIdByPrinciple(principal),reportDTO);
        return new ResponseEntity<>("Report Submitted",HttpStatus.CREATED);
    }


    @GetMapping("/reports/today") //for both manager and rep
    public ResponseEntity<Boolean> hasSubmittedReport(Principal principal){
        return new ResponseEntity<>(employeeService.getReportToday(findIdByPrinciple(principal)),HttpStatus.OK);
    }
    @GetMapping("/attendance/today") //for both manager and rep
    public ResponseEntity<Boolean> hasMarkedAttendance(Principal principal){
        return new ResponseEntity<>(employeeService.getAttendanceToday(findIdByPrinciple(principal)),HttpStatus.OK);
    }

    @GetMapping({"/representatives", "/managers"})//only for manager and admins
    public ResponseEntity<Page<EmployeeDTO>> getSubordinates(Principal principal,
                                                             @RequestParam(defaultValue = "0")int page,
                                                             @RequestParam(defaultValue = "10")int size){
        return new ResponseEntity<>(employeeService.getSubordinates(findIdByPrinciple(principal),PageRequest.of(page,size)),HttpStatus.OK);
    }

    @GetMapping({"/representatives/{id}/attendance","/managers/{id}/attendance"})
    public ResponseEntity<Page<LocalDate>> getPresentDates(@PathVariable Long id, @RequestParam(name="month",defaultValue = "-1") int month, @RequestParam(name="year",defaultValue = "-1") int year){
        if(month<0)
            month=LocalDate.now().getMonthValue();
        if(year<0)
            year=LocalDate.now().getYear();
        return new ResponseEntity<>(employeeService.getPresentDates(id,month,year),HttpStatus.OK);

    }

    @GetMapping({"/representatives/{id}/reports", "/managers/{id}/reports"})
    public ResponseEntity<Page<ReportDTO>> getReports(@PathVariable Long id,

                                                      @RequestParam(defaultValue = "0")int page,
                                                      @RequestParam(defaultValue = "10")int size){

        return new ResponseEntity<>(employeeService.getReports(id,
                PageRequest.of(page,size, Sort.Direction.DESC,"date")),HttpStatus.OK);
    }

    @GetMapping({"/representatives/{id}/retailers","managers/{id}/distributors"})
    public ResponseEntity<Page<CustomerDTO>> getRetailers(@PathVariable Long id,
                                                          @RequestParam(defaultValue = "0")int page,
                                                          @RequestParam(defaultValue = "10")int size){
        return new ResponseEntity<>(employeeService.getCustomers(id,PageRequest.of(page,size)),HttpStatus.OK);
    }
}
