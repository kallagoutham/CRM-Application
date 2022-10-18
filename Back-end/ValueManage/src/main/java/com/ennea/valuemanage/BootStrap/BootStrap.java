package com.ennea.valuemanage.BootStrap;

import com.ennea.valuemanage.Model.*;
import com.ennea.valuemanage.Model.security.Authority;
import com.ennea.valuemanage.Model.security.User;
import com.ennea.valuemanage.Repositories.security.AuthorityRepository;
import com.ennea.valuemanage.Repositories.security.UserRepository;
import com.ennea.valuemanage.Services.CustomerService;
import com.ennea.valuemanage.Services.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BootStrap implements CommandLineRunner {

    EmployeeService employeeService;
    CustomerService customerService;
    UserRepository userRepository;
    AuthorityRepository authorityRepository;

    PasswordEncoder passwordEncoder;
    public BootStrap(EmployeeService employeeService, CustomerService customerService,
                     UserRepository userRepository, AuthorityRepository authorityRepository,
                     PasswordEncoder passwordEncoder) {
        this.employeeService = employeeService;
        this.customerService = customerService;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        Customer dist1=Customer.builder().name("Distributor1")
                .customerType(CustomerType.DISTRIBUTOR)
                .phoneNo("9855135777").build();
        Customer dist2=Customer.builder().name("Distributor2")
                .customerType(CustomerType.DISTRIBUTOR)
                .phoneNo("9855135777").build();
        Customer dist3=Customer.builder().name("Distributor3")
                .customerType(CustomerType.DISTRIBUTOR)
                .phoneNo("9855135777").build();
        Customer dist4=Customer.builder().name("Distributor4")
                .customerType(CustomerType.DISTRIBUTOR)
                .phoneNo("9855135777").build();
        Customer dist5=Customer.builder().name("Distributor5")
                .customerType(CustomerType.DISTRIBUTOR)
                .phoneNo("9855135777").build();
        Customer dist6=Customer.builder().name("Distributor6")
                .customerType(CustomerType.DISTRIBUTOR)
                .phoneNo("9855135777").build();

        Employee m1=Employee.builder().employeeRole(EmployeeRole.MANAGER).name("Manager1").phoneNo("9848033221").customer(dist1).customer(dist2)
                .attendance(Attendance.builder().presenceDate(LocalDate.of(2022,10,1)).build())
                .attendance(Attendance.builder().presenceDate(LocalDate.of(2022,10,12)).build())
                .attendance(Attendance.builder().presenceDate(LocalDate.of(2022,3,12)).build()).build();
        Employee m2=Employee.builder().employeeRole(EmployeeRole.MANAGER).name("Manager2").phoneNo("9848033221").customer(dist3).customer(dist4).build();
        Employee m3=Employee.builder().employeeRole(EmployeeRole.MANAGER).name("Manager3").phoneNo("9848033221").customer(dist5).customer(dist6).build();



        Customer retailer1 = Customer.builder().name("Retailer1")
                .customerType(CustomerType.RETAILER)
                .phoneNo("9000800268")
                .order(Order.builder().productName("dolo").quantity(1999).build())
                .order(Order.builder().productName("xyantol").quantity(122).build())
                .order(Order.builder().productName("refsidol").quantity(399).build())
                .comment(Comment.builder().date(LocalDate.now()).name("Roy").text("LOL").build())
                .comment(Comment.builder().date(LocalDate.now()).name("James").text("Yeet").build())
                .comment(Comment.builder().date(LocalDate.now()).name("Dunken").text("XOXO").build())
                .comment(Comment.builder().date(LocalDate.now()).name("Kenny").text("peek").build())
                .build();
        Customer retailer2 = Customer.builder().name("Retailer2")
                .customerType(CustomerType.RETAILER)
                .phoneNo("9000800268").build();
        Customer retailer3 = Customer.builder().name("Retailer3")
                .customerType(CustomerType.RETAILER)
                .phoneNo("9000800268").build();
        Customer retailer4 = Customer.builder().name("Retailer4")
                .customerType(CustomerType.RETAILER)
                .phoneNo("9000800268").build();
        Customer retailer5 = Customer.builder().name("Retailer5")
                .customerType(CustomerType.RETAILER)
                .phoneNo("9000800268").build();
        Customer retailer6 = Customer.builder().name("Retailer6")
                .customerType(CustomerType.RETAILER)
                .phoneNo("9000800268").build();
        Customer retailer7 = Customer.builder().name("Retailer7")
                .customerType(CustomerType.RETAILER)
                .phoneNo("9000800268").build();
        Customer retailer8 = Customer.builder().name("Retailer8")
                .customerType(CustomerType.RETAILER)
                .phoneNo("9000800268").build();


        Employee re1=Employee.builder().name("Representative1").employeeRole(EmployeeRole.REPRESENTATIVE).supervisor(m1).customer(retailer1).build();
        Employee re2=Employee.builder().name("Representative2").employeeRole(EmployeeRole.REPRESENTATIVE).supervisor(m1).customer(retailer2).build();
        Employee re3=Employee.builder().name("Representative3").employeeRole(EmployeeRole.REPRESENTATIVE).supervisor(m2).customer(retailer3).build();
        Employee re4=Employee.builder().name("Representative4").employeeRole(EmployeeRole.REPRESENTATIVE).supervisor(m2).customer(retailer4).build();
        Employee re5=Employee.builder().name("Representative5").employeeRole(EmployeeRole.REPRESENTATIVE).supervisor(m3).customer(retailer5).build();
        Employee re6=Employee.builder().name("Representative6").employeeRole(EmployeeRole.REPRESENTATIVE).supervisor(m3).customer(retailer6).build();
        Employee re7=Employee.builder().name("Representative7").employeeRole(EmployeeRole.REPRESENTATIVE).supervisor(m1).customer(retailer7).build();
        Employee re8=Employee.builder().name("Representative8").employeeRole(EmployeeRole.REPRESENTATIVE).supervisor(m2).customer(retailer8)
                .report(Report.builder().date(LocalDate.now().minusDays(4)).totalMet(12).ordersPlaced(3).build())
                .report(Report.builder().date(LocalDate.now().plusDays(5)).totalMet(19).ordersPlaced(3).build())
                .report(Report.builder().date(LocalDate.now()).totalMet(20).ordersPlaced(3).build())
                .report(Report.builder().date(LocalDate.now().plusDays(1)).totalMet(40).ordersPlaced(3).build())
                .build();

        re1=employeeService.save(re1);
        re2=employeeService.save(re2);
        re3=employeeService.save(re3);
        re4=employeeService.save(re4);
        re5=employeeService.save(re5);
        re6=employeeService.save(re6);
        re7=employeeService.save(re7);
        re8=employeeService.save(re8);

        Authority managerAuthority = authorityRepository.save(Authority.builder().role("MANAGER").build());
        Authority representativeAuthority = authorityRepository.save(Authority.builder().role("REPRESENTATIVE").build());

        userRepository.save(User.builder().userName("Representative1").password(passwordEncoder.encode("password1")).authority(representativeAuthority).employee(re1).build());
        userRepository.save(User.builder().userName("Representative2").password(passwordEncoder.encode("password2")).authority(representativeAuthority).employee(re2).build());
        userRepository.save(User.builder().userName("Representative3").password(passwordEncoder.encode("password3")).authority(representativeAuthority).employee(re3).build());
        userRepository.save(User.builder().userName("Representative4").password(passwordEncoder.encode("password4")).authority(representativeAuthority).employee(re4).build());
        userRepository.save(User.builder().userName("Representative5").password(passwordEncoder.encode("password5")).authority(representativeAuthority).employee(re5).build());
        userRepository.save(User.builder().userName("Representative6").password(passwordEncoder.encode("password6")).authority(representativeAuthority).employee(re5).build());
        userRepository.save(User.builder().userName("Representative7").password(passwordEncoder.encode("password7")).authority(representativeAuthority).employee(re6).build());
        userRepository.save(User.builder().userName("Representative8").password(passwordEncoder.encode("password8")).authority(representativeAuthority).employee(re7).build());

        userRepository.save(User.builder().userName("Manager1").password(passwordEncoder.encode("password1")).authority(managerAuthority).employee(m1).build());
        userRepository.save(User.builder().userName("Manager2").password(passwordEncoder.encode("password2")).authority(managerAuthority).employee(m2).build());
        userRepository.save(User.builder().userName("Manager3").password(passwordEncoder.encode("password3")).authority(managerAuthority).employee(m3).build());

    }
}
