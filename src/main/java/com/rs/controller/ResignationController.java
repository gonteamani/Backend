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

import com.rs.dto.ResignationDto;
import com.rs.service.ResignationService;

@RestController
@RequestMapping("/Resignation")
public class ResignationController {

    @Autowired
    private ResignationService resignationService;

    @PostMapping("/apply/{id}")
    public ResponseEntity<String> applyResignation(@PathVariable Long id, @RequestBody ResignationDto dto) {
        return resignationService.applyResignation(id, dto);
    }
    @GetMapping("/fetch")
	public ResponseEntity<List<ResignationDto>> getAll(){
		return resignationService.getAll();
	}
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return resignationService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return resignationService.deleteById(id);
    }
    @PutMapping("/update/{id}")
	public ResponseEntity<?>updateById(@PathVariable long id, @RequestBody ResignationDto data){
		return resignationService.updateById(id,data);
	}


}