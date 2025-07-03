package com.rs.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.dto.LeaveRequestDto;
import com.rs.service.LeaveRequestService;


@RestController
@RequestMapping("/LeaveRequest")
public class LeaveRequestController {
		@Autowired  
		private LeaveRequestService service;

		@PostMapping("/add")
		public ResponseEntity<String> add(@RequestBody LeaveRequestDto data){
			return service.add(data);
		}

		@GetMapping("/fetch")
		public ResponseEntity<List<LeaveRequestDto>> getAll(){
			return service.getAll();
		}
		 @GetMapping("/getById/{id}")
		    public ResponseEntity<?> getById(@PathVariable Long id) {
		        return service.getById(id);
		    }

		 @DeleteMapping("/deleteById/{id}")
		    public ResponseEntity<String> deleteById(@PathVariable Long id) 
		 {
		        return service.deleteById(id);
		 }
		 @DeleteMapping("/delete/{id}")
		 public ResponseEntity<String> delete(@PathVariable Long id) {
		     return service.delete(id);
		 }

        @PutMapping("/update/{id}")
       public ResponseEntity<?>updateLeave(@PathVariable long id, @RequestBody LeaveRequestDto data){
	      return service.updateLeave(id,data);
         }
}
