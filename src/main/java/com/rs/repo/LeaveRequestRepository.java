package com.rs.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rs.Entity.Employee;
import com.rs.Entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long>{

	Optional<LeaveRequest> findByEndDate(String endDate);

//	Optional<LeaveRequest> findByMobileNo(String mobileNo);

//	Optional<LeaveRequest> findByStatus(String status);
//
//	Optional<LeaveRequest> findByStatusAndEndDate(String status, String endDate);

	//Optional<LeaveRequest> findByStatusAndEndDateAndEmployee(String status, String endDate, Employee empData);

	List<LeaveRequest> findByEndDateAndStatusAndEmployee(String endDate, String status, Employee emp);


	

}