package com.ennea.valuemanage.Services;

import com.ennea.valuemanage.Model.Attendance;
import com.ennea.valuemanage.Repositories.AttendanceRepository;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance save(Attendance attendance){
//        attendance.setPresenceDate(LocalDate.now());
        return attendanceRepository.save(attendance);
    }
}
