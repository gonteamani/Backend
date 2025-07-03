package com.rs.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rs.Entity.Employee;
import com.rs.Entity.LeaveRequest;
import com.rs.dto.LeaveRequestDto;
import com.rs.exception.DuplicateFound;
import com.rs.exception.ResourceNotFound;
import com.rs.repo.EmployeeRepository;
import com.rs.repo.LeaveRequestRepository;

@Service
public class LeaveRequestImplementation implements LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRepo;
    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
  private ModelMapper modelmapper;

    @Override	
    public ResponseEntity<String> add(LeaveRequestDto data) {
		String status=data.getStatus();
		String mobileNo = data.getMobileNo();
		String endDate = data.getEndDate();
		Employee emp = empRepo.findByMobileNo(mobileNo).orElseThrow(() -> new ResourceNotFound("Employee not found for mobile number: " + data.getMobileNo()));
		
		        List<LeaveRequest> existingLeaves = leaveRepo.findByEndDateAndStatusAndEmployee(endDate,status,emp);
		        if (!existingLeaves.isEmpty()) {
		            throw new DuplicateFound("Leave already applied for the given end date: " + endDate);
		        }
		        else {
		        //  Map DTO to entity 
		        LeaveRequest leaveRequest = new LeaveRequest();
		        leaveRequest.setEmployee(emp);
		        leaveRequest.setStartDate(data.getStartDate());
		        leaveRequest.setEndDate(data.getEndDate());;
		        leaveRequest.setReason(data.getReason());
		        leaveRequest.setStatus(data.getStatus());
		        leaveRepo.save(leaveRequest);
		        
		        return ResponseEntity.status(HttpStatus.OK).body("Leave request submitted successfully");
		    }
    }

@Override
public ResponseEntity<List<LeaveRequestDto>> getAll() {
    List<LeaveRequest> data = leaveRepo.findAll();
    if (data.isEmpty()) {
        throw new ResourceNotFound("Employee records not found");
    }
    List<LeaveRequestDto> dtoData = data.stream()
        .map(d -> modelmapper.map(d, LeaveRequestDto.class))
        .collect(Collectors.toList());
    return ResponseEntity.ok(dtoData);
}

@Override
    public ResponseEntity<?> getById(Long id) {
        try {
            Optional<LeaveRequest> data = leaveRepo.findById(id);

            if (data.isPresent()) {
                LeaveRequestDto dto = modelmapper.map(data.get(), LeaveRequestDto.class);
                return ResponseEntity.ok(dto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

   @Override
    public ResponseEntity<String> deleteById(Long id) {
        try {
            Optional<LeaveRequest> data = leaveRepo.findById(id);

            if (data.isPresent()) {
                leaveRepo.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Employee no content with ID: " + id);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
   @Override
   public ResponseEntity<String> delete(Long id) {
       try {
           Optional<LeaveRequest> optionalLeave = leaveRepo.findById(id);

           if (optionalLeave.isEmpty()) {
               return ResponseEntity.status(HttpStatus.NOT_FOUND)
                       .body("Leave request not found with ID: " + id);
           }

           LeaveRequest leave = optionalLeave.get();
           String currentStatus = leave.getStatus().trim().toLowerCase();

           if (currentStatus.equals("request") || currentStatus.equals("pending")) {
               leaveRepo.deleteById(id);
               return ResponseEntity.ok("Leave request deleted successfully with ID: " + id);
           } else {
               return ResponseEntity.status(HttpStatus.FORBIDDEN)
                       .body("Cannot delete leave request with status: " + leave.getStatus());
           }

       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("Error occurred: " + e.getMessage());
       }
   }
   @Override
   public ResponseEntity<String> updateLeave(long id, LeaveRequestDto data) {
       LeaveRequest existingLeave = leaveRepo.findById(id)
           .orElseThrow(() -> new ResourceNotFound("Leave request with ID " + id + " not found."));

       String currentStatus = existingLeave.getStatus().trim().toLowerCase();
       if (currentStatus.equals("request") || currentStatus.equals("pending")) {
          
           DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // ISO format
           LocalDate oldStartDate = LocalDate.parse(existingLeave.getStartDate(), format);
           LocalDate newStartDate = LocalDate.parse(data.getEndDate(), format);
           if (newStartDate.isAfter(oldStartDate)) {
               existingLeave.setStartDate(data.getStartDate());
               existingLeave.setEndDate(data.getEndDate());
               existingLeave.setReason(data.getReason());
               existingLeave.setStatus(data.getStatus());

               leaveRepo.save(existingLeave);

               return ResponseEntity.status(HttpStatus.OK).body("Leave request updated successfully.");
           }
           else {
        	   return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                       .body("New end date must be after existing start date.");
           }
           
       }
       
   else {
   return ResponseEntity.status(HttpStatus.FORBIDDEN)
           .body("Cannot update leave with status: " + existingLeave.getStatus());
   }
}

   

}