package com.ennea.valuemanage.Repositories;

import com.ennea.valuemanage.Model.Attendance;
import com.ennea.valuemanage.Model.Customer;
import com.ennea.valuemanage.Model.Employee;
import com.ennea.valuemanage.Model.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("select cust from Customer cust where cust.agent.id=?1")
    public Page<Customer> findAllCustomers(Long id, PageRequest pageRequest);

    @Query("select attendance.presenceDate from Attendance attendance where attendance.employee.id=?1 and attendance.presenceDate between ?2 and ?3")
    Page<LocalDate> findAllPresentDates(Long id, LocalDate st_date, LocalDate end_date,PageRequest pageRequest);

    @Query("select report from Report report where report.employee.id=?1")
    Page<Report> findAllReports(Long id,PageRequest pageRequest);

    @Query("select report from Report report where report.employee.id=?1 and report.date=?2")
    Optional<Report> findTodaysReport(Long id, LocalDate now);

    @Query("select employee from Employee employee where employee.supervisor.id=?1")
    Page<Employee> findAllSubordinates(Long id,PageRequest pageRequest);

    @Query("select attendance from Attendance attendance where attendance.employee.id=?1 and attendance.presenceDate=?2")
    Optional<Attendance> getTodaysAttendance(Long id, LocalDate now);
}
