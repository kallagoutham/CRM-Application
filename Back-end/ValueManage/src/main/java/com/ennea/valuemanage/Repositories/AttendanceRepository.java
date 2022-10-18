package com.ennea.valuemanage.Repositories;

import com.ennea.valuemanage.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
}
