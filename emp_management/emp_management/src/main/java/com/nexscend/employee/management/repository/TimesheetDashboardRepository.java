package com.nexscend.employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexscend.employee.management.entity.TimesheetDashboard;

@Repository
public interface TimesheetDashboardRepository extends JpaRepository<TimesheetDashboard, Integer> {

}