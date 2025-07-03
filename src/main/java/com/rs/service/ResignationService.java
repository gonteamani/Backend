package com.rs.service;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.rs.dto.ResignationDto;

public interface ResignationService {
    public ResponseEntity<List<ResignationDto>> getAll();
	public ResponseEntity<?> getById(Long id);

	 public ResponseEntity<String> deleteById(Long id);
	public ResponseEntity<String> updateById(long id, ResignationDto dto);

	public ResponseEntity<String> applyResignation(Long id, ResignationDto dto);

}