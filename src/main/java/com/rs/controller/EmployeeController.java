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

import com.rs.dto.EmployeeDto;
import com.rs.dto.LeaveRequestDto;
import com.rs.service.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {
	@Autowired
	EmployeeService service;
	@PostMapping("/add")
	public ResponseEntity<String>add(@RequestBody EmployeeDto data){
		return service.add(data);
	}
	@GetMapping("/fetch")
	public ResponseEntity<List<EmployeeDto>> getAll(){
		return service.getAll();
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id){
	return service.getById(id);
	}
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		return service.deleteById(id);
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<?>updateById(@PathVariable long id, @RequestBody EmployeeDto data){
		return service.updateById(id,data);
	}	
}
